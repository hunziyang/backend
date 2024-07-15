package com.techking.portal.security.service.impl.roleService;

import com.techking.portal.core.utils.pagination.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class RoleSelectVo {

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("分页设置")
    @NotNull(message = "分页不能为空")
    private @Valid Pagination pagination;
}
