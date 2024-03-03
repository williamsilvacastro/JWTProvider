package br.com.wscastro.JWTProvider.Infra;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;


@Data
public class RestErrorMessage {
    private HttpStatus status;
    private String message;
    private Date timestamp;


    public RestErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }


}
