package com.maxmendes.votacaoapi.contract.exception;

import com.maxmendes.votacaoapi.contract.exception.error.SimpleError;
import com.maxmendes.votacaoapi.impl.error.AlreadyVoteException;
import com.maxmendes.votacaoapi.impl.error.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends Throwable {

    @ExceptionHandler(AlreadyVoteException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseEntity<SimpleError> serverExceptionHandler(Exception ex, HttpServletRequest request) {
        log.info("FUNCIONOU " + ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                .body(SimpleError.builder()
                        .statusCode(HttpStatus.PRECONDITION_FAILED.value())
                        .timestamp(System.currentTimeMillis())
                        .path(request.getRequestURI())
                        .description(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<SimpleError> notFoundHandler(Exception ex, HttpServletRequest request) {
        log.info("FUNCIONOU " + ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(SimpleError.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .timestamp(System.currentTimeMillis())
                        .path(request.getRequestURI())
                        .description(ex.getMessage())
                        .build());
    }
}
