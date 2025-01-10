package com.api.controle_estacionamento.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                              HttpServletRequest request) {
        log.error("Validation error - ", exception);
        BindingResult result = exception.getBindingResult();
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Erro de validação nos campos informados",
                        result
                ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                              HttpServletRequest request) {
        log.error("Database integrity violation - ", ex);
        ErrorMessage errorMessage;
        if (ex.getMessage() != null && ex.getMessage().contains("username_unico")) {
            errorMessage = new ErrorMessage(
                    request,
                    HttpStatus.CONFLICT,
                    "O username fornecido já está em uso."
            );
        } else {
            errorMessage = new ErrorMessage(
                    request,
                    HttpStatus.BAD_REQUEST,
                    "Erro de integridade no banco de dados."
            );
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception,
                                                                              HttpServletRequest request) {
        log.error("Api error - ", exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.NOT_FOUND,
                        exception.getMessage()
                ));
    }

}
