package com.techking.portal.core.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@EnableTransactionManagement
@Configuration
@MapperScan("com.techking.portal.**.mapper")
public class MybatisConfig implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdTime", ZonedDateTime.class, ZonedDateTime.now(ZoneId.systemDefault()));
        this.strictInsertFill(metaObject, "updatedTime", ZonedDateTime.class, ZonedDateTime.now(ZoneId.systemDefault()));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updatedTime", ZonedDateTime.class, ZonedDateTime.now(ZoneId.systemDefault()));
    }
}
