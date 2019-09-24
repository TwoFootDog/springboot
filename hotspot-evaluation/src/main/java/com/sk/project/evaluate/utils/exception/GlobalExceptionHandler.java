package com.sk.project.evaluate.utils.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<Object> handleServiceException(ServiceException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        ExceptionDto dto = new ExceptionDto(e.getErrMsg());

        return new ResponseEntity<>(dto, responseHeaders, HttpStatus.BAD_REQUEST);
    }
}
