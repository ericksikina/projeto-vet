package com.bellapet.utils.exceptions.handler;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.bellapet.utils.exceptions.models.EstruturaErro;
import com.bellapet.utils.exceptions.models.ValidationError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<EstruturaErro> validationException(MethodArgumentNotValidException error, HttpServletRequest request) {

        ValidationError validationError = new ValidationError(System.currentTimeMillis(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação de Campos!",
                error.getMessage(), request.getRequestURI());

        error.getBindingResult().getFieldErrors().forEach(fieldError -> validationError.addError(
                fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<EstruturaErro> badCredentialsException(BadCredentialsException exception, HttpServletRequest request) {
        EstruturaErro erro = new EstruturaErro(
                System.currentTimeMillis(),
                HttpStatus.UNAUTHORIZED.value(),
                "Login ou senha inválidos!",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<EstruturaErro> illegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        EstruturaErro erro = new EstruturaErro(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Requisição!",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EstruturaErro> entityNotFoundException(EntityNotFoundException exception, HttpServletRequest request) {
        EstruturaErro erro = new EstruturaErro(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Entidade não encontrada!",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<EstruturaErro> jWTCreationException(JWTCreationException exception, HttpServletRequest request) {
        EstruturaErro erro = new EstruturaErro(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro ao gerar token JWT!",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    private ResponseEntity<EstruturaErro> erroPadronizado(HttpStatus httpStatus, String mensagemGenericaErro,
                                                          Exception exception, HttpServletRequest request) {

        return ResponseEntity.status(httpStatus).body(new EstruturaErro(System.currentTimeMillis(), httpStatus.value(),
                mensagemGenericaErro, exception.getMessage(), request.getRequestURI()));
    }
}
