package com.tsypk.sniper.exception;

import com.tsypk.sniper.network.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author tsypk on 25.02.2022 15:55
 * @project sniper
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Response> handleBadRequest(BadRequestException exception) {
        Response response = new Response(exception.getMessage());
        log.warn("[handleBadRequest] there is bad request: {}", response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<Response> handleAnyException(Exception exception) {
        Response response = new Response(exception.getMessage());
        log.warn("[handleAnyException] there is exception: {}", response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
