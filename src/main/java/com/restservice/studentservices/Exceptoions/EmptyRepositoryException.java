package com.restservice.studentservices.Exceptoions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyRepositoryException extends RuntimeException
{
    public EmptyRepositoryException(String message) {
        super(message);
    }
}
