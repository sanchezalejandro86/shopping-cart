package ar.com.garbarino.repository;

import ar.com.garbarino.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Product, String>{
}
