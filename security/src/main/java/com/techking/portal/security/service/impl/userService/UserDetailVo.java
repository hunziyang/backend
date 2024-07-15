package com.techking.portal.security.service.impl.userService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class UserDetailVo {

    @NotBlank(message = "名字不能为空")
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("性别")
    @NotNull(message = "性别不能为空")
    private Integer gender;
}
