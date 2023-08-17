package com.yassine.employeemanager.exception;

public class UseNotFoundException extends RuntimeException {
    public UseNotFoundException(String message) {
        super(message);
    }
}
