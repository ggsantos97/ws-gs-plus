package com.client.ws.gsplus.exception.handler;

import com.client.ws.gsplus.dto.error.ErrorResponseDto;
import com.client.ws.gsplus.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> notFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildError(notFoundException.getMessage(),HttpStatus.NOT_FOUND));
    }

    private ErrorResponseDto buildError(String message, HttpStatus status){
        return ErrorResponseDto.builder()
                .message(message)
                .httpStatus(status)
                .statusCode(status.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> badRequestException(MethodArgumentNotValidException m) {

        Map<String, String> messages = new HashMap<>();
        m.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            messages.put(field, defaultMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildError(Arrays.toString(messages.entrySet().toArray()),HttpStatus.BAD_REQUEST));
    }

}
