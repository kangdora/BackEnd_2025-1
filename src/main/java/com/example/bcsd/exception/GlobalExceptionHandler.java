package com.example.bcsd.exception;

import com.example.bcsd.exception.common.BaseException;
import com.example.bcsd.exception.common.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(new ErrorResponse(
                        ex.getErrorCode().name(),
                        ex.getErrorCode().getMessage()
                ));
    }
}
