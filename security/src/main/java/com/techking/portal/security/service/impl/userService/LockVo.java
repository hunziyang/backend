package com.techking.portal.security.service.impl.userService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class LockVo {

    @NotNull(message = "锁状态不能为空")
    @ApiModelProperty("锁状态")
    private Boolean locked;
}
