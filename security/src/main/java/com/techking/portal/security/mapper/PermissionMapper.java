package com.techking.portal.security.mapper;

import com.techking.portal.core.mybatis.TechkingMapper;
import com.techking.portal.security.entity.Permission;
import com.techking.portal.security.service.impl.permissionService.PermissionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper extends TechkingMapper<Permission> {

    List<Permission> getPermission(@Param("query") PermissionVo permissionVo, @Param("offset") int offset, @Param("pageSize") int pageSize);

    int getPermissionCount(@Param("query") PermissionVo permissionVo);

    List<Permission> getPermissions();

    List<Permission> getPermissionsByUserId(@Param("id") Long id);
}
