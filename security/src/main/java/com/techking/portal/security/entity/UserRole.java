package com.techking.portal.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.techking.portal.core.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户角色(UserRole)实体类
 *
 * @author makejava
 * @since 2024-07-23 17:07:49
 */
@Data
@Accessors(chain = true)
@TableName("USER_ROLE")
public class UserRole extends BaseEntity {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;

}

