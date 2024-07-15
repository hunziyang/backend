package com.techking.portal.techkingbackend.controller;

import com.techking.portal.core.annotation.TechkingController;
import com.techking.portal.core.result.Result;
import com.techking.portal.security.annotation.UrlPass;
import org.springframework.web.bind.annotation.GetMapping;

@TechkingController("/test")
public class TestController {

    @UrlPass
    @GetMapping
    public Result test() {
        return  Result.success();
    }
}
