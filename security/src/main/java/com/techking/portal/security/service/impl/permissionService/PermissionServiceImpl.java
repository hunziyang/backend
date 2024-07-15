package com.techking.portal.security.service.impl.permissionService;

import com.techking.portal.core.exception.BaseException;
import com.techking.portal.core.utils.pagination.PagedList;
import com.techking.portal.security.SecurityConstant;
import com.techking.portal.security.entity.Permission;
import com.techking.portal.security.mapper.PermissionMapper;
import com.techking.portal.security.result.ResultSecurityCode;
import com.techking.portal.security.service.PermissionService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PagedList<Permission> getPermission(PermissionVo permissionVo) {
        permissionVo.getPagination().checkOrder();
        List<Permission> permissions = permissionMapper.getPermission(permissionVo, permissionVo.getPagination().getOffset(), permissionVo.getPagination().getPageSize());
        int count = permissionMapper.getPermissionCount(permissionVo);
        return PagedList
                .<Permission>builder()
                .data(permissions)
                .total(count)
                .pageNum(permissionVo.getPagination().getPageNum())
                .pageSize(permissionVo.getPagination().getPageSize())
                .build();
    }

    @Override
    public List<PermissionTreeDto> getPermissionTree() {
        List<Permission> permissions = permissionMapper.getPermissions();
        Map<Long, List<Permission>> parentIdPermissionMap = permissions.stream().sorted(Comparator.comparing(Permission::getOrder)).collect(Collectors.groupingBy(Permission::getParentId));
        if (!parentIdPermissionMap.containsKey(SecurityConstant.Permission.ROOT_PARENT_ID)) {
            throw new BaseException(ResultSecurityCode.NO_CATALOG_PERMISSION);
        }
        List<PermissionTreeDto> permissionTreeDtos = new ArrayList<>();
        parentIdPermissionMap.get(SecurityConstant.Permission.ROOT_PARENT_ID).forEach(permission ->
            createPermissionTreeDto(permission, parentIdPermissionMap, permissionTreeDtos, null)
        );
        return permissionTreeDtos;
    }

    private void createPermissionTreeDto(Permission permission, Map<Long, List<Permission>> parentIdPermissionMap, List<PermissionTreeDto> permissionTreeDtos, PermissionTreeDto parentPermissionTreeDto) {
        PermissionTreeDto permissionTreeDto = new PermissionTreeDto();
        if (permission.getParentId() == 0L && ObjectUtils.isEmpty(parentPermissionTreeDto)) {
            permissionTreeDtos.add(permissionTreeDto);
        }
        if (ObjectUtils.isNotEmpty(parentPermissionTreeDto)){
            parentPermissionTreeDto.getChildren().add(permissionTreeDto);
        }
        BeanUtils.copyProperties(permission, permissionTreeDto);
        List<Permission> permissions = parentIdPermissionMap.get(permissionTreeDto.getId());
        if (ObjectUtils.isEmpty(permissions)) {
            return;
        }
        List<PermissionTreeDto> childrenPermissionTreeDtoList = new ArrayList<>();
        permissionTreeDto.setChildren(childrenPermissionTreeDtoList);
        permissions.forEach(sonPermission -> createPermissionTreeDto(sonPermission, parentIdPermissionMap, permissionTreeDtos, permissionTreeDto));
    }
}
