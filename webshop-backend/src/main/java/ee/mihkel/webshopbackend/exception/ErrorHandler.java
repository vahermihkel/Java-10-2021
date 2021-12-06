package ee.mihkel.webshopbackend.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NoSuchElementException e) {
        ErrorResponse response = new ErrorResponse(
                new Date(),
                "Otsitud ressurssi ei leitud",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(EmptyResultDataAccessException e) {
        ErrorResponse response = new ErrorResponse(
                new Date(),
                "Otsitud ressurssi ei leitud",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
