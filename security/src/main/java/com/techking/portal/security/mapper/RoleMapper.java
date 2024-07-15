package com.techking.portal.security.mapper;

import com.techking.portal.core.mybatis.TechkingMapper;
import com.techking.portal.security.entity.Role;
import com.techking.portal.security.service.impl.roleService.RoleSelectVo;
import org.apache.ibatis.annotations.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface RoleMapper extends TechkingMapper<Role> {
    int deleteRole(@Param("id") Long id, @Param("updatedBy") Long updatedBy, @Param("updatedTime") ZonedDateTime updatedTime);

    List<Role> selectRoles(@Param("query") RoleSelectVo roleSelectVo,@Param("offset") int offset,@Param("pageSize") int pageSize);

    int selectRoleCount(@Param("query") RoleSelectVo roleSelectVo);

    Role selectById(@Param("id") Long id);

    List<Role> selectRolesByUserId(@Param("id") Long userId);

    List<Role> selectAll();
}
