package com.techking.portal.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.techking.portal.core.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2024-07-15 11:25:33
 */
@Data
@Accessors(chain = true)
@TableName("USER")
public class User extends BaseEntity {

    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别:
     * 0：男
     * 1：女
     * 2: 未知
     */
    private Integer gender;
    /**
     * 是否锁号
     */
    private Boolean isLock;
}

