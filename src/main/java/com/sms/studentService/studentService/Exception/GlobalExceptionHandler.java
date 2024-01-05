package com.sms.studentService.studentService.Exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/*
this class will only handle controller related exceptions.
 if in future you use any filters it wont handle them.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchResourceException.class)
    public ResponseEntity<ApiResponse> noSuchExceptionHandler(NoSuchResourceException exception)
    {
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(),new Date()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> constraintVoilationException(ConstraintViolationException constraintViolationException)
    {
        ApiResponse apiResponse = new ApiResponse(constraintViolationException.getMessage(), new Date());
        return new ResponseEntity<>(apiResponse,HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
