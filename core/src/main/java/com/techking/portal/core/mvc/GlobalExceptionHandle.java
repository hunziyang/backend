package com.techking.portal.core.mvc;


import com.techking.portal.core.exception.BaseException;
import com.techking.portal.core.result.Result;
import com.techking.portal.core.result.ResultCode;
import com.techking.portal.core.utils.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public Result exception(Exception exception) {
        log.warn("exception err:{}", exception.getMessage(), exception);
        return baseException(new BaseException(ResultCode.INTERNAL_SERVER_ERROR, MapUtil.getErrMap(exception.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : exception.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return baseException(new BaseException(ResultCode.BAD_REQUEST, errors));
    }

    @ExceptionHandler(BindException.class)
    public Result bindException(BindException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : exception.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return baseException(new BaseException(ResultCode.BAD_REQUEST, errors));
    }

    @ExceptionHandler(BaseException.class)
    public Result baseException(BaseException exception) {
        log.warn("baseException message:{},errors:{}", exception.getMessage(), exception.getErrors(), exception);
        return Result.error(exception.getResultCodeBase(), exception.getErrors());
    }
}
