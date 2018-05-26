package ar.com.garbarino.repository;

import ar.com.garbarino.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alejandro on 23/04/18.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String>, ProductRepositoryCustom{
}
