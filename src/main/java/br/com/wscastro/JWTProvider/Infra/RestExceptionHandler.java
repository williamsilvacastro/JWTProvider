package br.com.wscastro.JWTProvider.Infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    private ResponseEntity erroeExceptionHandler(Exception e){

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage()));
    }
}
