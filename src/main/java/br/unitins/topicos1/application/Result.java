package br.unitins.topicos1.application;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation; 
public class Result {

    private String code;
    private String message;
    private boolean success;

    public Result(String message, String code) {
        this.code = code;
        this.success = true;
        this.message = message;
    }

    public Result(String code, String message, boolean success) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public Result(Set<? extends ConstraintViolation<?>> violations) {
        this.code = "400";
        this.success = false;
        this.message = violations.stream()
             .map(cv -> cv.getMessage())
             .collect(Collectors.joining(", "));
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

}
