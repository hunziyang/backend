package com.techking.portal.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.techking.portal.core.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色权限表(RolePermission)实体类
 *
 * @author makejava
 * @since 2024-07-23 11:12:36
 */
@Data
@TableName("ROLE_PERMISSION")
@Accessors(chain = true)
public class RolePermission extends BaseEntity {

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 权限ID
     */
    private Long permissionId;

}

