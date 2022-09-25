package br.com.davimgoncalves.oceiro.exception;

import br.com.davimgoncalves.oceiro.dto.ErroDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDTO handleValidationError(MethodArgumentNotValidException exception, HttpServletRequest request) {
        var errorMessage = new HashMap<String, String>();
        exception.getBindingResult().getFieldErrors().forEach(e -> errorMessage.put(e.getField(), e.getDefaultMessage()));
        return logAndReturn(HttpStatus.BAD_REQUEST, errorMessage.toString(), request.getServletPath());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroDTO handleUsernameNotFoundException(BadCredentialsException exception, HttpServletRequest request) {
        return logAndReturn(HttpStatus.NOT_FOUND, exception.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroDTO handleNotFoundError(NotFoundException exception, HttpServletRequest request) {
        return logAndReturn(HttpStatus.NOT_FOUND, exception.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroDTO handleException(Exception exception, HttpServletRequest request) {
        return logAndReturn(HttpStatus.INTERNAL_SERVER_ERROR, null, request.getServletPath());
    }

    private ErroDTO logAndReturn(HttpStatus status, String message, String path) {
        log.error("method={}; httpStatus={}; message={}; path={}", "logAndReturn", status, message, path);
        return new ErroDTO(status, message, path);
    }
}
