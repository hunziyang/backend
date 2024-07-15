package com.techking.portal.security.mapper;

import com.techking.portal.core.mybatis.TechkingMapper;
import com.techking.portal.security.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.time.ZonedDateTime;

public interface UserRoleMapper extends TechkingMapper<UserRole> {
    int deleteByUserId(@Param("id") Long id, @Param("updatedBy") Long userId, @Param("updatedTime") ZonedDateTime now);
    int deleteByRoleId(@Param("id") Long id, @Param("updatedBy") Long userId, @Param("updatedTime") ZonedDateTime now);
}
