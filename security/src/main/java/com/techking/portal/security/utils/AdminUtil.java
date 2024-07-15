package com.techking.portal.security.utils;

import com.techking.portal.core.exception.BaseException;
import com.techking.portal.security.result.ResultSecurityCode;

public class AdminUtil {

    public static void checkIsAdmin(Long userId) {
        if (userId == 1L) {
            throw new BaseException(ResultSecurityCode.ADMIN_CANNOT_OPERATE);
        }
    }
}
