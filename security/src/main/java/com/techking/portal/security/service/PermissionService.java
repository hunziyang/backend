package com.techking.portal.security.service;

import com.techking.portal.core.utils.pagination.PagedList;
import com.techking.portal.security.entity.Permission;
import com.techking.portal.security.service.impl.permissionService.PermissionTreeDto;
import com.techking.portal.security.service.impl.permissionService.PermissionVo;

import java.util.List;

public interface PermissionService {
    PagedList<Permission> getPermission(PermissionVo permissionVo);

    List<PermissionTreeDto> getPermissionTree();
}
