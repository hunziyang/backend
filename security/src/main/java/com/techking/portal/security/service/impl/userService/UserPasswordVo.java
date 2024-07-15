package com.techking.portal.security.service.impl.userService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class UserPasswordVo {

    @ApiModelProperty("旧密码")
    @NotBlank(message = "旧密码不能为空")
    private String password;

    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
