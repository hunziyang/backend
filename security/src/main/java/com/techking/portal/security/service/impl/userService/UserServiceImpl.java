package com.techking.portal.security.service.impl.userService;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.techking.portal.core.CoreConstant;
import com.techking.portal.core.exception.BaseException;
import com.techking.portal.core.utils.UpdateUtil;
import com.techking.portal.core.utils.pagination.PagedList;
import com.techking.portal.security.SecurityConstant;
import com.techking.portal.security.UsersContextHolder;
import com.techking.portal.security.entity.Permission;
import com.techking.portal.security.entity.Role;
import com.techking.portal.security.entity.User;
import com.techking.portal.security.entity.UserRole;
import com.techking.portal.security.mapper.PermissionMapper;
import com.techking.portal.security.mapper.RoleMapper;
import com.techking.portal.security.mapper.UserMapper;
import com.techking.portal.security.mapper.UserRoleMapper;
import com.techking.portal.security.result.ResultSecurityCode;
import com.techking.portal.security.service.UserService;
import com.techking.portal.security.service.impl.permissionService.PermissionTreeDto;
import com.techking.portal.security.utils.AdminUtil;
import com.techking.portal.security.utils.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void register(RegisterVo registerVo) {
        try {
            String salt = UUID.randomUUID().toString().replace("-", "");
            User newUser = new User()
                    .setUsername(registerVo.getUsername())
                    .setSalt(salt)
                    .setPassword(DigestUtils.md5Hex(String.format("%s%s", registerVo.getPassword(), salt)))
                    .setName(registerVo.getName());
            Optional.ofNullable(registerVo.getGender()).ifPresent(newUser::setGender);
            userMapper.insert(newUser);
        } catch (DuplicateKeyException e) {
            throw new BaseException(ResultSecurityCode.USER_EXIST);
        }
    }

    @Override
    public LoginDto login(LoginVo loginVo) {
        User user = userMapper.selectByUserName(loginVo.getUsername());
        if (ObjectUtils.isEmpty(user)) {
            throw new BaseException(ResultSecurityCode.USER_NOT_EXIST);
        }
        String password = DigestUtils.md5Hex(String.format("%s%s", loginVo.getPassword(), user.getSalt()));
        if (!password.equals(user.getPassword())) {
            throw new BaseException(ResultSecurityCode.PASSWORD_ERROR);
        }
        if (user.getIsLock()) {
            throw new BaseException(ResultSecurityCode.LOCKED);
        }
        List<Permission> permissions = permissionMapper.getPermissionsByUserId(user.getId());
        Set<String> permissionCodes = permissions.stream()
                .filter(permission -> permission.getType() == 1 || permission.getType() == 2)
                .map(Permission::getCode).collect(Collectors.toSet());
        List<PermissionTreeDto> menus = getMenus(permissions);
        String jwt = JwtUtil.sign(user.getUsername());
        LoginDto loginDto = new LoginDto()
                .setJwt(jwt)
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPermissionCodes(permissionCodes)
                .setMenus(menus);
        redisTemplate.opsForValue().set(jwt, loginDto, SecurityConstant.Jwt.EXPIRE_HOUR, TimeUnit.HOURS);
        return loginDto;
    }

    /**
     * 获取目录
     *
     * @param permissions
     * @return
     */
    private List<PermissionTreeDto> getMenus(List<Permission> permissions) {
        List<PermissionTreeDto> permissionTreeDtos = new ArrayList<>();
        Map<Long, PermissionTreeDto> idPermissionTreeDtoMap = new HashMap<>();
        List<Permission> menus = permissions.stream()
                .filter(permission -> permission.getType() == 0 || permission.getType() == 1)
                .sorted(Comparator.comparing(Permission::getType).thenComparing(Permission::getOrder))
                .collect(Collectors.toList());
        menus.stream().forEach(permission -> {
            PermissionTreeDto permissionTreeDto = new PermissionTreeDto();
            BeanUtils.copyProperties(permission, permissionTreeDto);
            idPermissionTreeDtoMap.put(permissionTreeDto.getId(), permissionTreeDto);
            if (permissionTreeDto.getType() == 0
                    || !idPermissionTreeDtoMap.containsKey(permissionTreeDto.getParentId())) {
                permissionTreeDtos.add(permissionTreeDto);
                return;
            }
            PermissionTreeDto parentPermissionTreeDto = idPermissionTreeDtoMap.get(permissionTreeDto.getParentId());
            if (ObjectUtils.isEmpty(parentPermissionTreeDto.getChildren())) {
                parentPermissionTreeDto.setChildren(new ArrayList<PermissionTreeDto>());
            }
            parentPermissionTreeDto.getChildren().add(permissionTreeDto);
        });
        return permissionTreeDtos;
    }

    @Override
    public void updateLock(Long id, LockVo lockVo) {
        AdminUtil.checkIsAdmin(id);
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getIsLock, lockVo.getLocked())
                .set(User::getIsLock, lockVo.getLocked())
                .set(User::getUpdatedBy, UsersContextHolder.getUserId())
                .set(User::getUpdatedTime, ZonedDateTime.now(ZoneId.systemDefault()))
                .eq(User::getId, id)
                .eq(User::getIsDeleted, CoreConstant.IS_DELETED);
        UpdateUtil.updateCheck(userMapper.update(lambdaUpdateWrapper));
    }

    @Override
    public void updateDetail(UserDetailVo userDetailVo) {
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getName, userDetailVo.getName())
                .set(User::getGender, userDetailVo.getGender())
                .set(User::getUpdatedBy, UsersContextHolder.getUserId())
                .set(User::getUpdatedTime, ZonedDateTime.now(ZoneId.systemDefault()))
                .eq(User::getId, UsersContextHolder.getUserId())
                .eq(User::getIsDeleted, CoreConstant.IS_DELETED);
        UpdateUtil.updateCheck(userMapper.update(lambdaUpdateWrapper));
    }

    @Override
    public void logout() {
        redisTemplate.delete(UsersContextHolder.getToken());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AdminUtil.checkIsAdmin(id);
        int update = userMapper.deleteUser(id, UsersContextHolder.getUserId(), ZonedDateTime.now(ZoneId.systemDefault()));
        UpdateUtil.updateCheck(update);
        userRoleMapper.deleteByUserId(id, UsersContextHolder.getUserId(), ZonedDateTime.now(ZoneId.systemDefault()));
    }

    @Override
    public PagedList<User> select(UserSelectVo userSelectVo) {
        userSelectVo.getPagination().checkOrder();
        List<User> users = userMapper.selectUsers(userSelectVo, userSelectVo.getPagination().getOffset(), userSelectVo.getPagination().getPageSize());
        int count = userMapper.selectUserCount(userSelectVo);
        return PagedList.<User>builder()
                .data(users)
                .total(count)
                .pageNum(userSelectVo.getPagination().getPageNum())
                .pageSize(userSelectVo.getPagination().getPageSize())
                .build();
    }

    @Override
    public void updatePassword(UserPasswordVo userPasswordVo) {
        Long userId = UsersContextHolder.getUserId();
        User user = userMapper.selectById(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new BaseException(ResultSecurityCode.USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(DigestUtils.md5Hex(String.format("%s%s", userPasswordVo.getPassword(), user.getSalt())))) {
            throw new BaseException(ResultSecurityCode.PASSWORD_ERROR);
        }
        String newPassword = DigestUtils.md5Hex(String.format("%s%s", userPasswordVo.getNewPassword(), user.getSalt()));
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getPassword, newPassword)
                .set(User::getUpdatedBy, userId)
                .set(User::getUpdatedTime, ZonedDateTime.now(ZoneId.systemDefault()))
                .eq(User::getId, userId)
                .eq(User::getIsDeleted, CoreConstant.IS_DELETED);
        int update = userMapper.update(lambdaUpdateWrapper);
        UpdateUtil.updateCheck(update);
        redisTemplate.delete(UsersContextHolder.getToken());
    }

    @Override
    public void resetPassword(Long id, UserResetPasswordVo userResetPasswordVo) {
        User user = userMapper.selectById(id);
        if (ObjectUtils.isEmpty(user)) {
            throw new BaseException(ResultSecurityCode.USER_NOT_EXIST);
        }
        String password = DigestUtils.md5Hex(String.format("%s%s", userResetPasswordVo.getPassword(), user.getSalt()));
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getPassword, password)
                .set(User::getUpdatedBy, UsersContextHolder.getUserId())
                .set(User::getUpdatedTime, ZonedDateTime.now(ZoneId.systemDefault()))
                .eq(User::getId, id)
                .eq(User::getIsDeleted, CoreConstant.IS_DELETED);
        int update = userMapper.update(lambdaUpdateWrapper);
        UpdateUtil.updateCheck(update);
    }

    @Override
    public UserDetailDto detail(Long id) {
        User user = userMapper.selectById(id);
        if (ObjectUtils.isEmpty(user)) {
            throw new BaseException(ResultSecurityCode.USER_NOT_EXIST);
        }
        List<Role> roles = roleMapper.selectRolesByUserId(id);
        UserDetailDto userDetailDto = new UserDetailDto();
        BeanUtils.copyProperties(user, userDetailDto);
        userDetailDto.setRoles(roles);
        return userDetailDto;
    }

    @Override
    @Transactional
    public void updateDetailIncludeRoles(Long id, UpdateDetailIncludeRolesVo updateDetailIncludeRolesVo) {
        AdminUtil.checkIsAdmin(id);
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getName, updateDetailIncludeRolesVo.getName())
                .set(User::getGender, updateDetailIncludeRolesVo.getGender())
                .set(User::getUpdatedBy, UsersContextHolder.getUserId())
                .set(User::getUpdatedTime, ZonedDateTime.now(ZoneId.systemDefault()))
                .eq(User::getId, id)
                .eq(User::getIsDeleted, CoreConstant.IS_DELETED);
        UpdateUtil.updateCheck(userMapper.update(lambdaUpdateWrapper));
        userRoleMapper.deleteByUserId(id, UsersContextHolder.getUserId(), ZonedDateTime.now(ZoneId.systemDefault()));
        updateDetailIncludeRolesVo.getRoles().forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(id);
            userRole.setRoleId(roleId);
            userRole.setCreatedBy(UsersContextHolder.getUserId());
            UpdateUtil.updateCheck(userRoleMapper.insert(userRole));
        });
    }
}
