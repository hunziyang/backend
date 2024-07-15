package com.techking.portal.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.techking.portal.core.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色(Role)实体类
 *
 * @author makejava
 * @since 2024-07-15 15:55:59
 */
@Data
@TableName("ROLE")
@Accessors(chain = true)
public class Role extends BaseEntity {
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String description;
}

