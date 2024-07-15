package com.techking.portal.security.service;

import com.techking.portal.core.utils.pagination.PagedList;
import com.techking.portal.security.entity.User;
import com.techking.portal.security.service.impl.userService.*;

public interface UserService {
    void register(RegisterVo registerVo);

    LoginDto login(LoginVo loginVo);

    void updateLock(Long id, LockVo lockVo);

    void updateDetail(UserDetailVo userDetailVo);

    void logout();

    void delete(Long id);

    PagedList<User> select(UserSelectVo userSelectVo);

    void updatePassword(UserPasswordVo userPasswordVo);

    void resetPassword(Long id, UserResetPasswordVo userResetPasswordVo);

    UserDetailDto detail(Long id);

    void updateDetailIncludeRoles(Long id, UpdateDetailIncludeRolesVo updateDetailIncludeRolesVo);
}
