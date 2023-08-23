package com.github.ikhlashmulya.springrestapi.exception;

import com.github.ikhlashmulya.springrestapi.model.WebResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<WebResponse<?>> resourceNotFoundException(Exception exception) {
        return new ResponseEntity<>(
                WebResponse.builder().status("failed").errors(exception.getMessage()).build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<WebResponse<?>> constraintViolationsException(Exception exception) {
        return new ResponseEntity<>(
                WebResponse.builder().status("failed").errors(exception.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<?>> globalException(Exception exception) {
        return new ResponseEntity<>(
                WebResponse.builder().status("failed").errors(exception.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
