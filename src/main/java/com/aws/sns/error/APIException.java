package com.aws.sns.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
class APIException{
    private String message;
    private HttpStatus status;
    private LocalDateTime time;
}
