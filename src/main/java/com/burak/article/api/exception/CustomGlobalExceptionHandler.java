package com.burak.article.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler {


    @ExceptionHandler(value
            = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleCVException(ConstraintViolationException ex,
                                                    WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(x -> x.getMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        ex.printStackTrace();

        return ResponseEntity.badRequest().body(body);

    }

    @ExceptionHandler(value
            = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHMNRException(HttpMessageNotReadableException ex,
                                                      WebRequest request) {
        String body = "Json Parse Error" + ex.getCause();
        return ResponseEntity.badRequest().body(body);

    }


}
