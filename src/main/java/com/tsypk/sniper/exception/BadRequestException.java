package com.tsypk.sniper.exception;

/**
 * @author tsypk on 25.02.2022 14:50
 * @project sniper
 */
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
