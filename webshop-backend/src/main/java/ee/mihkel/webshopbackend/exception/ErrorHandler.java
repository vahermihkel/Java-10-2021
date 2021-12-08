package ee.mihkel.webshopbackend.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.NoSuchElementException;

@Log4j2
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

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ConstraintViolationException e) {
        ErrorResponse response = new ErrorResponse(
                new Date(),
                "Kohustuslikud väljad on sisestamata",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserExistsException e) {
        ErrorResponse response = new ErrorResponse(
                new Date(),
                "Sellise isikukoodiga kasutaja on juba olemas",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(EmailExistsException e) {
        ErrorResponse response = new ErrorResponse(
                new Date(),
                "Sellise e-mailiga kasutaja on juba olemas",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
