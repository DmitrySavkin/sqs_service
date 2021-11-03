package com.aws.sns.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class NotificationErrorHandling {


    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleApiRequestException(RuntimeException e) {
        HttpStatus badRequest  = HttpStatus.BAD_REQUEST;
        APIException exception =  APIException.builder()
                                            .message(e.getMessage())
                                            .time(LocalDateTime.now())
                                            .status(badRequest)
                                            .build();
        return new ResponseEntity<>(exception, badRequest);
    }



}
