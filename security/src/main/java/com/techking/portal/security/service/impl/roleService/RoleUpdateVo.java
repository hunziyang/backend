package com.techking.portal.security.service.impl.roleService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel
public class RoleUpdateVo {

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("权限列表")
    private List<Long> permissionIds;
}
