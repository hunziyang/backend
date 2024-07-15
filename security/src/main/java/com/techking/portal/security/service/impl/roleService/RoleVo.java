package com.techking.portal.security.service.impl.roleService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel
public class RoleVo {

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty("名称")
    private String name;

    @NotBlank(message = "角色编码不能为空")
    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("权限列表")
    private List<Long> permissionIds;
}
