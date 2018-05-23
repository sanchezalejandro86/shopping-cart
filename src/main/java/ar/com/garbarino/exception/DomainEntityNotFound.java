package ar.com.garbarino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alejandro on 23/04/18.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No se encontró la entidad")
public class DomainEntityNotFound extends RuntimeException {

    public DomainEntityNotFound(String message) {
        super(message);
    }

}
