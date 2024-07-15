package com.techking.portal.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.techking.portal.core.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 权限(Permission)实体类
 *
 * @author makejava
 * @since 2024-07-22 16:44:40
 */
@Data
@TableName("PERMISSION")
@Accessors(chain = true)
public class Permission extends BaseEntity {

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限编码
     */
    private String code;
    /**
     * 权限类型;0：目录 1：菜单 2：按钮
     */
    private Integer type;
    /**
     * 父节点
     */
    private Long parentId;
    /**
     * 排序
     */
    private Integer order;
    /**
     * 图像
     */
    private String icon;
    /**
     * 路径
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     * 描述
     */
    private String description;
}

