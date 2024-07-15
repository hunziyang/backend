package com.techking.portal.security.service.impl.userService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class RegisterVo {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

    @NotBlank(message = "名字不能为空")
    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    @NotNull(message = "性别不能为空")
    private Integer gender;
}
