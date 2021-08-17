package com.restservice.studentservices.Controller;

import com.restservice.studentservices.Exceptoions.EmptyRepositoryException;
import com.restservice.studentservices.Exceptoions.EntityNotFoundException;
import com.restservice.studentservices.Model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomApiExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllExceptions(Exception ex, WebRequest request)
    {
        Error error =
                new Error(LocalDateTime.now(), ex.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Error>
    entityNotFoundExceptionHandler(EntityNotFoundException ex,
                                   WebRequest webRequest)
    {
        Error error =
                new Error(LocalDateTime.now(), ex.getMessage(),
                        webRequest.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyRepositoryException.class)
    public final ResponseEntity<Error>
    emptyRepositoryExceptionHandler(EmptyRepositoryException ex,
                                    WebRequest webRequest)
    {
        Error error =
                new Error(LocalDateTime.now(), ex.getMessage(),
                        webRequest.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
