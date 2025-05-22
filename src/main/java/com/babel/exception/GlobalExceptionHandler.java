package com.babel.exception;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExceptionGenericException.class)
    public ResponseEntity<String> handleGenericException(ExceptionGenericException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("Error in data", HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(HandlerMethodValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach(error -> {
            String field = error instanceof FieldError ? ((FieldError) error).getField() : ((ObjectError) error).getObjectName();
            errors.put(field, error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
    
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> handleDateTimeParseException(DateTimeParseException ex) {
        return new ResponseEntity<>("Date Invalid, you need dd-mm-yyyy format", HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResourceException(NoResourceFoundException ex) {
        return new ResponseEntity<>("This endpoint does not exist.", HttpStatus.NOT_FOUND);
    }
    
}
