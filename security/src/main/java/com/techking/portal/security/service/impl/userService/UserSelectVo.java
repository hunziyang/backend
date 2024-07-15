package com.techking.portal.security.service.impl.userService;

import com.techking.portal.core.utils.pagination.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class UserSelectVo {

    @NotNull(message = "页码信息不能为空")
    private @Valid Pagination pagination;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("名称")
    private String name;
}
