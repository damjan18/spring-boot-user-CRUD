package com.example.usercrud.config;

import com.example.usercrud.user.exception.UniqueViolationException;
import com.example.usercrud.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> notFound(UserNotFoundException ex) {
        return Map.of("error","NOT_FOUND","message", ex.getMessage());
    }

    @ExceptionHandler(UniqueViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,Object> conflict(UniqueViolationException ex) {
        return Map.of("error","CONFLICT","message", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> validation(MethodArgumentNotValidException ex) {
        var first = ex.getBindingResult().getFieldErrors().stream().findFirst();
        return Map.of("error","VALIDATION",
                "message", first.map(e -> e.getField()+": "+e.getDefaultMessage()).orElse("Invalid payload"));
    }
}
