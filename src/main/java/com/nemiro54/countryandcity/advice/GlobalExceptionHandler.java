package com.nemiro54.countryandcity.advice;

import com.nemiro54.countryandcity.dto.error.ExceptionMessageResponse;
import com.nemiro54.countryandcity.exception.notfound.NotFoundException;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ExceptionMessageResponse> handleAuthenticationException(Exception ex) {
    ExceptionMessageResponse response = new ExceptionMessageResponse(
        Instant.now(), "Authentication Failed", ex.getMessage()
    );
    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ExceptionMessageResponse> handleAccessDeniedException(Exception ex) {
    ExceptionMessageResponse response = new ExceptionMessageResponse(
        Instant.now(), "Not Enough Rights", ex.getMessage()
    );
    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionMessageResponse> handleNotFoundException(Exception ex) {
    ExceptionMessageResponse response = new ExceptionMessageResponse(
        Instant.now(), "Not Found", ex.getMessage()
    );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}
