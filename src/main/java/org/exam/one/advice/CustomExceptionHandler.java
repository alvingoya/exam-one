package org.exam.one.advice;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler
{
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String,String>> handleException(Exception e) {
    return ResponseEntity.internalServerError().body(Map.of("errorMessage", e.getMessage()));
  }
}
