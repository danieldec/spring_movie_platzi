package com.platzi.play.web.Exception;

import com.platzi.play.dominio.exception.MovieAlredyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MovieAlredyExistsException.class)
    public ResponseEntity<Error> handleException(Exception ex){
        var error = new Error("movie-alredy-exists",ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex){
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errors.add(new Error(error.getField(),error.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleExceptionGeneral(Exception ex){
        var error = new Error("unknown-error",ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
