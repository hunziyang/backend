package com.techking.portal.security.service.impl.roleService;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.techking.portal.core.CoreConstant;
import com.techking.portal.core.exception.BaseException;
import com.techking.portal.core.utils.UpdateUtil;
import com.techking.portal.core.utils.pagination.PagedList;
import com.techking.portal.security.SecurityConstant;
import com.techking.portal.security.UsersContextHolder;
import com.techking.portal.security.entity.Permission;
import com.techking.portal.security.entity.Role;
import com.techking.portal.security.entity.RolePermission;
import com.techking.portal.security.mapper.PermissionMapper;
import com.techking.portal.security.mapper.RoleMapper;
import com.techking.portal.security.mapper.RolePermissionMapper;
import com.techking.portal.security.mapper.UserRoleMapper;
import com.techking.portal.security.result.ResultSecurityCode;
import com.techking.portal.security.service.RoleService;
import com.techking.portal.security.utils.AdminUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional
    public void insert(RoleVo roleVo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);
        role.setCreatedBy(UsersContextHolder.getUserId());
        roleMapper.insert(role);
        if (ObjectUtils.isEmpty(roleVo.getPermissionIds())) {
            return;
        }
        roleVo.getPermissionIds().forEach(permissionId -> {
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(role.getId())
                    .setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        });
    }

    @Override
    @Transactional
    public void update(Long id, RoleUpdateVo roleUpdateVo) {
        AdminUtil.checkIsAdmin(id);
        LambdaUpdateWrapper<Role> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(Role::getName, roleUpdateVo.getName())
                .set(Role::getDescription, roleUpdateVo.getDescription())
                .eq(Role::getId, id)
                .eq(Role::getIsDeleted, CoreConstant.IS_DELETED);
        int update = roleMapper.update(lambdaUpdateWrapper);
        UpdateUtil.updateCheck(update);
        rolePermissionMapper.deleteByRoleId(id, UsersContextHolder.getUserId(), ZonedDateTime.now(ZoneId.systemDefault()));
        if (ObjectUtils.isEmpty(roleUpdateVo.getPermissionIds())) {
            return;
        }
        roleUpdateVo.getPermissionIds().forEach(permissionId -> {
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(id)
                    .setPermissionId(permissionId);
            rolePermission.setCreatedBy(UsersContextHolder.getUserId());
            rolePermissionMapper.insert(rolePermission);
        });
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AdminUtil.checkIsAdmin(id);
        int update = roleMapper.deleteRole(id, UsersContextHolder.getUserId(), ZonedDateTime.now(ZoneId.systemDefault()));
        UpdateUtil.updateCheck(update);
        rolePermissionMapper.deleteByRoleId(id, UsersContextHolder.getUserId(), ZonedDateTime.now(ZoneId.systemDefault()));
        userRoleMapper.deleteByRoleId(id, UsersContextHolder.getUserId(), ZonedDateTime.now(ZoneId.systemDefault()));
    }

    @Override
    public PagedList<Role> select(RoleSelectVo roleSelectVo) {
        roleSelectVo.getPagination().checkOrder();
        List<Role> roles = roleMapper.selectRoles(roleSelectVo, roleSelectVo.getPagination().getOffset(), roleSelectVo.getPagination().getPageSize());
        int count = roleMapper.selectRoleCount(roleSelectVo);
        return PagedList
                .<Role>builder()
                .data(roles)
                .total(count)
                .pageNum(roleSelectVo.getPagination().getPageNum())
                .pageSize(roleSelectVo.getPagination().getPageSize())
                .build();
    }

    @Override
    public RoleDetailDto detail(Long id) {
        Role role = roleMapper.selectById(id);
        if (ObjectUtils.isEmpty(role)) {
            throw new BaseException(ResultSecurityCode.ROLE_NOT_EXIST);
        }
        List<Long> permissionIds = rolePermissionMapper.getPermissionByRoleId(id);
        List<Permission> permissions = permissionMapper.getPermissions();
        Map<Long, List<Permission>> parentIdPermissionMap = permissions.stream().sorted(Comparator.comparing(Permission::getOrder)).collect(Collectors.groupingBy(Permission::getParentId));
        if (!parentIdPermissionMap.containsKey(SecurityConstant.Permission.ROOT_PARENT_ID)) {
            throw new BaseException(ResultSecurityCode.NO_CATALOG_PERMISSION);
        }
        List<RolePermissionTreeDto> rolePermissionTreeDtos = new ArrayList<>();
        parentIdPermissionMap.get(SecurityConstant.Permission.ROOT_PARENT_ID).forEach(permission ->
                createRolePermissionTreeDto(permission, parentIdPermissionMap, rolePermissionTreeDtos, null, permissionIds)
        );
        return RoleDetailDto.builder()
                .role(role)
                .rolePermissionTreeDtos(rolePermissionTreeDtos)
                .build();
    }

    private void createRolePermissionTreeDto(Permission permission, Map<Long, List<Permission>> parentIdPermissionMap, List<RolePermissionTreeDto> rolePermissionTreeDtos, RolePermissionTreeDto parentRolePermissionTreeDto, List<Long> permissionIds) {
        RolePermissionTreeDto rolePermissionTreeDto = new RolePermissionTreeDto();
        if (permission.getParentId() == SecurityConstant.Permission.ROOT_PARENT_ID && ObjectUtils.isEmpty(parentRolePermissionTreeDto)) {
            rolePermissionTreeDtos.add(rolePermissionTreeDto);
        }
        if (ObjectUtils.isNotEmpty(parentRolePermissionTreeDto)) {
            parentRolePermissionTreeDto.getChildren().add(rolePermissionTreeDto);
        }
        BeanUtils.copyProperties(permission, rolePermissionTreeDto);
        if (permissionIds.contains(rolePermissionTreeDto.getId())) {
            rolePermissionTreeDto.setHasPermission(true);
        }
        List<Permission> permissions = parentIdPermissionMap.get(rolePermissionTreeDto.getId());
        if (ObjectUtils.isEmpty(permissions)) {
            return;
        }
        List<RolePermissionTreeDto> childrenPermissionTreeDtoList = new ArrayList<>();
        rolePermissionTreeDto.setChildren(childrenPermissionTreeDtoList);
        permissions.forEach(sonPermission -> createRolePermissionTreeDto(sonPermission, parentIdPermissionMap, rolePermissionTreeDtos, rolePermissionTreeDto, permissionIds));
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }
}
