package com.mylearning.loans.exception;

import com.mylearning.loans.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError error : validationErrors) {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            body.put(fieldName, validationMsg);
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDto errorDto = new ErrorDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {LoanAlreadyExistException.class})
    public ResponseEntity<ErrorDto> handleLoanAlreadyExistException(LoanAlreadyExistException ex, WebRequest request) {
        ErrorDto errorDto = new ErrorDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDto> handleRuntimeException(Exception ex, WebRequest request) {

        ErrorDto errorDto = new ErrorDto(
                request.getDescription(false),
                HttpStatus.BAD_GATEWAY,
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_GATEWAY);
    }
}
