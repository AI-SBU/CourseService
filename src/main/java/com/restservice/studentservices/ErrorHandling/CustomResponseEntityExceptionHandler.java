package com.restservice.studentservices.ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request)
    {
        ErrorDetails errorDetails =
                new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public final ResponseEntity<ErrorDetails> entityNotFoundExceptionHandler(EntityNotFoundException ex,
                                                                             WebRequest webRequest)
    {
        ErrorDetails errorDetails =
                new ErrorDetails(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
