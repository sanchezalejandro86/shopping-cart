package ar.com.garbarino.repository;

import ar.com.garbarino.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by alejandro on 23/05/18.
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Autowired
    public ProductRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Product discountStock(String id, long quantity) {
         return this.mongoTemplate.findAndModify(new Query(
                where("id").is(id)),
                new Update().inc("stock", -quantity), Product.class);
    }
}
