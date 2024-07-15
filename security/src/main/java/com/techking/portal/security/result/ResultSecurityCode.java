package com.techking.portal.security.result;

import com.techking.portal.core.result.ResultCodeBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultSecurityCode implements ResultCodeBase {

    USER_EXIST(801, "用户已存在"),
    USER_NOT_EXIST(802, "用户不存在"),
    PASSWORD_ERROR(803, "密码错误"),
    LOCKED(804, "用户被锁定，请联系管理员解锁"),
    AUTHENTICATION_FAILED(805, "身份认证失败"),
    PERMISSION_DENIED(806, "无权限"),
    NO_CATALOG_PERMISSION(901,"没有根目录"),
    ROLE_NOT_EXIST(1001,"角色不存在"),
    ADMIN_CANNOT_OPERATE(1101,"超级管理员不允许操作");

    private Integer code;
    private String message;
}
