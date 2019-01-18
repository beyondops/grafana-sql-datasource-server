package com.beyondops.sqlquery.config;

import com.beyondops.sqlquery.model.grafana.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/18.
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(e.getMessage());
        return errorResponse;
    }
}
