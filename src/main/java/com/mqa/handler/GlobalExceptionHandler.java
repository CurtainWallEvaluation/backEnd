package com.mqa.handler;

import com.mqa.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result<String> excptionHandler(RuntimeException exception){
        log.info("exception message:{}",exception.getMessage());
        return Result.error(exception.getMessage());
    }
}
