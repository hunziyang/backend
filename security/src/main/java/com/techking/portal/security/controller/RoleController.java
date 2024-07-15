package com.techking.portal.security.controller;

import com.techking.portal.core.annotation.TechkingController;
import com.techking.portal.core.result.Result;
import com.techking.portal.security.annotation.TechkingPermission;
import com.techking.portal.security.service.RoleService;
import com.techking.portal.security.service.impl.roleService.RoleSelectVo;
import com.techking.portal.security.service.impl.roleService.RoleUpdateVo;
import com.techking.portal.security.service.impl.roleService.RoleVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@TechkingController("/role")
@Api(tags = "角色")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色创建
     *
     * @param roleVo
     * @return
     */
    @TechkingPermission("role:insert")
    @PostMapping("/insert")
    @ApiOperation("角色创建")
    public Result insert(@Validated @RequestBody RoleVo roleVo) {
        roleService.insert(roleVo);
        return Result.success();
    }

    /**
     * 角色更新
     *
     * @param id
     * @param roleUpdateVo
     * @return
     */
    @TechkingPermission("role:update")
    @PutMapping("/update/{id}")
    @ApiOperation("角色更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "path")
    })
    public Result update(@PathVariable("id") Long id, @Validated @RequestBody RoleUpdateVo roleUpdateVo) {
        roleService.update(id, roleUpdateVo);
        return Result.success();
    }

    /**
     * 角色删除
     *
     * @param id
     * @return
     */
    @TechkingPermission("role:delete")
    @DeleteMapping("/delete/{id}")
    @ApiOperation("角色删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "path")
    })
    public Result delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return Result.success();
    }

    @TechkingPermission("role:select")
    @GetMapping
    @ApiOperation("角色查询")
    public Result select(@Validated RoleSelectVo roleSelectVo) {
        return Result.success(roleService.select(roleSelectVo));
    }

    @TechkingPermission("role:detail")
    @GetMapping("/detail/{id}")
    @ApiOperation("角色详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "path")
    })
    public Result detail(@PathVariable("id") Long id) {
        return Result.success(roleService.detail(id));
    }

    @TechkingPermission("role:selectAll")
    @GetMapping("/selectAll")
    @ApiOperation("获取全部角色-适配于下拉列表")
    public Result selectAll() {
        return Result.success(roleService.selectAll());
    }
}
