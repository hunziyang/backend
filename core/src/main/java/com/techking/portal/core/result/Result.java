package com.techking.portal.core.result;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Result<T> {

    private Integer code;
    private String message;
    private T data;
    private Map<String, ?> errors;

    public static Result success() {
        return Result.builder().code(ResultCode.SUCCESS.getCode()).message(ResultCode.SUCCESS.getMessage()).build();
    }

    public static <T> Result success(T data) {
        return Result.builder().code(ResultCode.SUCCESS.getCode()).message(ResultCode.SUCCESS.getMessage()).<T>data(data).build();
    }

    public static <T> Result error(ResultCodeBase resultCodeBase) {
        return Result.builder().code(resultCodeBase.getCode()).message(resultCodeBase.getMessage()).build();
    }

    public static <T> Result error(ResultCodeBase resultCodeBase, Map<String, ?> errors) {
        return Result.builder().code(resultCodeBase.getCode()).message(resultCodeBase.getMessage()).errors(errors).build();
    }
}
