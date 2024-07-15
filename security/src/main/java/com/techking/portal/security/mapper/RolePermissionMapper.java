package com.techking.portal.security.mapper;

import com.techking.portal.core.mybatis.TechkingMapper;
import com.techking.portal.security.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface RolePermissionMapper extends TechkingMapper<RolePermission> {

    int deleteByRoleId(@Param("roleId") Long roleId, @Param("updatedBy") Long userId, @Param("updatedTime") ZonedDateTime updatedTime);

    List<Long> getPermissionByRoleId(@Param("roleId") Long id);
}
