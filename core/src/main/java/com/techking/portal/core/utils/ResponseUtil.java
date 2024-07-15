package com.techking.portal.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techking.portal.core.jackson.JacksonConfig;
import com.techking.portal.core.result.Result;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseUtil {

    private static final ObjectMapper objectMapper = new JacksonConfig().objectMapper();

    @SneakyThrows
    public static void writeResponse(HttpServletResponse response, Result result) {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(result));
    }
}
