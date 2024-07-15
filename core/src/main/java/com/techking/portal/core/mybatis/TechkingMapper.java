package com.techking.portal.core.mybatis;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface TechkingMapper<T> extends BaseMapper<T> {

    default int update(LambdaUpdateWrapper<T> lambdaUpdateWrapper) {
        return update(null, lambdaUpdateWrapper);
    }
}
