package com.julian.curso.springboot.error.springboot_error.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.julian.curso.springboot.error.springboot_error.models.Error;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({ArithmeticException.class}) // Captura error division por cero
    public ResponseEntity<Error> divisionByZero(Exception ex){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error division por cero!");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        // Forma de dar mensaje de excepcion con ResponseEntity y status
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);

        // Una forma de dar mensaje de excepcion con ResponseEntity y propiedad internalServerError
        //return ResponseEntity.internalServerError().body(error);
        
    }

    @ExceptionHandler(NoHandlerFoundException.class) // Captura error 404
    public ResponseEntity<Error> notFoundEx(NoHandlerFoundException e){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Api rest no encontrado!");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(NumberFormatException.class) // Captura error conversion de numero
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> numberFormat(NumberFormatException ex){

        Map<String,String> error = new HashMap<>();
        error.put("date", new Date().toString());
        error.put("error", "Numero invalido, no tiene formato correcto!");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()+""); //Forma de convertir int a String
    
        return error;
    }
}
