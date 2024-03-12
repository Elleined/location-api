package com.elleined.philippinelocationapi.controller;

import com.elleined.philippinelocationapi.dto.Response;
import com.elleined.philippinelocationapi.exception.AlreadyExistsException;
import com.elleined.philippinelocationapi.exception.EmptyUUIDException;
import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ResourceNotFoundException.class, AlreadyExistsException.class, IOException.class})
    public ResponseEntity<Response> handleResourceNotFoundException(RuntimeException ex) {
        var response = new Response(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyUUIDException.class)
    public ResponseEntity<Response> handleEmptyUUIDException(EmptyUUIDException ex) {
        var response = new Response(ex.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<Response>> handleBindException(BindException ex) {
        List<Response> errors = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .map(errorMessage -> new Response(errorMessage, HttpStatus.BAD_REQUEST))
                .toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
