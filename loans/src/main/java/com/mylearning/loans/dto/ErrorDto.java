package com.mylearning.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDto {
    private String apiPath;
    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime timestamp;
}
