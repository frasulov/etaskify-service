package az.abb.etaskifyservice.controller;

import az.abb.etaskifyservice.config.Constants;
import az.abb.etaskifyservice.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        Response response = Response.builder()
                .message(Constants.VALIDATION_FAILED)
                .data(validationErrors)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGenericException(Exception ex) {
        Response response = Response.builder()
                .message(Constants.REQUEST_FAILED)
                .build();

        return ResponseEntity.badRequest().body(response);
    }
}