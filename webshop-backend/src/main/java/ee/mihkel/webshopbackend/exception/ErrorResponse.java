package ee.mihkel.webshopbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Date timeStamp;
    private String message;
    private HttpStatus httpStatus;
}
