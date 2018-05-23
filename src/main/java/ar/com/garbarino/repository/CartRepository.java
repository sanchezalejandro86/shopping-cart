package ar.com.garbarino.repository;

import ar.com.garbarino.domain.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alejandro on 23/04/18.
 */
@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
}
