package com.example.bcsd.exception;

import org.springframework.http.ResponseEntity;
import com.example.bcsd.dto.ErrorResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidUser(CustomException e) {
        ErrorCode code = e.getErrorCode();
        ErrorResponseDto response = ErrorResponseDto.builder()
                .code(code.name())
                .message(code.getDetail())
                .build();

        return ResponseEntity.status(code.getHttpStatus()).body(response);
    }
}
