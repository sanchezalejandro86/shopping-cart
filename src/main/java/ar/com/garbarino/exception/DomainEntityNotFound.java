package ar.com.garbarino.exception;

/**
 * Created by alejandro on 23/04/18.
 */
public class DomainEntityNotFound extends RuntimeException {

    public DomainEntityNotFound(String message) {
        super(message);
    }

}
