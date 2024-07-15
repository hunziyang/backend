package com.techking.portal.security.controller;

import com.techking.portal.core.annotation.TechkingController;
import com.techking.portal.core.result.Result;
import com.techking.portal.security.annotation.TechkingPermission;
import com.techking.portal.security.service.PermissionService;
import com.techking.portal.security.service.impl.permissionService.PermissionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@TechkingController("/permission")
@Api(tags = "权限")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @TechkingPermission("permission:select")
    @GetMapping
    @ApiOperation("权限查询")
    public Result getPermission(@Validated PermissionVo permissionVo) {
        return Result.success(permissionService.getPermission(permissionVo));
    }

    @TechkingPermission("permission:tree")
    @GetMapping("/tree")
    @ApiOperation("权限树")
    public Result getPermissionTree() {
        return Result.success(permissionService.getPermissionTree());
    }
}
