package com.techking.portal.security.service.impl.permissionService;

import com.techking.portal.core.utils.pagination.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class PermissionVo {

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限编码")
    private String code;

    @NotNull(message = "页码信息不能为空")
    private @Valid Pagination pagination;
}
