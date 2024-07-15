package com.techking.portal.security.controller;

import com.techking.portal.core.annotation.TechkingController;
import com.techking.portal.core.result.Result;
import com.techking.portal.security.UsersContextHolder;
import com.techking.portal.security.annotation.TechkingPermission;
import com.techking.portal.security.annotation.UrlPass;
import com.techking.portal.security.service.UserService;
import com.techking.portal.security.service.impl.userService.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@TechkingController("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param registerVo
     * @return
     */
    @UrlPass
    @PostMapping("/register")
    @ApiOperation("注册")
    public Result register(@Validated @RequestBody RegisterVo registerVo) {
        userService.register(registerVo);
        return Result.success();
    }

    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    @UrlPass
    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(@Validated @RequestBody LoginVo loginVo) {
        return Result.success(userService.login(loginVo));
    }

    /**
     * 更新用户锁定状态
     *
     * @param id
     * @param lockVo
     * @return
     */
    @TechkingPermission("user:updateLock")
    @PutMapping("/updateLock/{id}")
    @ApiOperation("更新用户锁状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    public Result updateLock(@PathVariable("id") Long id, @Validated @RequestBody LockVo lockVo) {
        userService.updateLock(id, lockVo);
        return Result.success();
    }

    @PutMapping("/updateDetail")
    @ApiOperation("更新用户信息")
    public Result updateDetail(@RequestBody UserDetailVo userDetailVo) {
        userService.updateDetail(userDetailVo);
        return Result.success();
    }

    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("登出")
    public Result logout() {
        userService.logout();
        return Result.success();
    }

    /**
     * 用户删除
     *
     * @param id
     * @return
     */
    @TechkingPermission("user:delete")
    @DeleteMapping("/delete/{id}")
    @ApiOperation("用户删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    public Result delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return Result.success();
    }

    /**
     * 用户查询
     *
     * @param userSelectVo
     * @return
     */
    @TechkingPermission("user:select")
    @GetMapping
    @ApiOperation("用户查询")
    public Result select(@Validated UserSelectVo userSelectVo) {
        return Result.success(userService.select(userSelectVo));
    }

    @PutMapping("/updatePassword")
    @ApiOperation("更新密码")
    public Result updatePassword(@Validated @RequestBody UserPasswordVo userPasswordVo) {
        userService.updatePassword(userPasswordVo);
        return Result.success();
    }

    @TechkingPermission("user:resetPassword")
    @PutMapping("/resetPassword/{id}")
    @ApiOperation("重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    public Result resetPassword(@PathVariable("id") Long id, @Validated @RequestBody UserResetPasswordVo userResetPasswordVo) {
        userService.resetPassword(id, userResetPasswordVo);
        return Result.success();
    }

    @TechkingPermission("user:detail")
    @GetMapping("/detail/{id}")
    @ApiOperation("获取用户详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    public Result detail(@PathVariable("id") Long id) {
        return Result.success(userService.detail(id));
    }

    @TechkingPermission("user:updateDetailIncludeRoles")
    @PutMapping("/updateDetailIncludeRoles/{id}")
    @ApiOperation("更新用户信息（包含角色）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    public Result updateDetailIncludeRoles(@PathVariable("id") Long id, @Validated @RequestBody UpdateDetailIncludeRolesVo updateDetailIncludeRolesVo) {
        userService.updateDetailIncludeRoles(id, updateDetailIncludeRolesVo);
        return Result.success();
    }

    @GetMapping("/userDetail")
    @ApiOperation("个人信息详情")
    public Result userDetail() {
        return Result.success(userService.detail(UsersContextHolder.getUserId()));
    }
}
