package ar.com.garbarino.repository;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.domain.Trace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alejandro on 26/04/18.
 */
@Repository
public interface TraceRepository extends MongoRepository<Trace, String> {
}