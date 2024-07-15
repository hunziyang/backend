package com.techking.portal.core.utils;

import com.techking.portal.core.exception.BaseException;
import com.techking.portal.core.result.ResultCode;

public class UpdateUtil {

    public static void updateCheck(int count){
        if (count == 0) {
            throw new BaseException(ResultCode.UPDATE_FAILED);
        }
    }
}
