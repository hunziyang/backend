package com.techking.portal.security.service.impl.userService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class UserResetPasswordVo {

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("重置密码")
    private String password;
}
