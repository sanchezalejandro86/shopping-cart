package ar.com.garbarino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alejandro on 23/04/18.
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="El cart se encuentra en un estado que ya no puede ser modificado")
public class CartStatusViolationException extends RuntimeException {

    public CartStatusViolationException(String message) {
        super(message);
    }

}
