package com.techking.portal.security.mapper;

import com.techking.portal.core.mybatis.TechkingMapper;
import com.techking.portal.security.entity.User;
import com.techking.portal.security.service.impl.userService.UserSelectVo;
import org.apache.ibatis.annotations.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface UserMapper extends TechkingMapper<User> {
    User selectByUserName(@Param("username") String username);

    int deleteUser(@Param("id") Long id, @Param("updatedBy") Long updatedBy, @Param("updatedTime") ZonedDateTime updatedTime);

    List<User> selectUsers(@Param("query") UserSelectVo userSelectVo, @Param("offset") int offset, @Param("pageSize") int pageSize);
    int selectUserCount(@Param("query") UserSelectVo userSelectVo);

    User selectById(@Param("id") Long id);
}
