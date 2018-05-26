package ar.com.garbarino.repository;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by alejandro on 23/05/18.
 */
public class CartRepositoryImpl implements CartRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Autowired
    public CartRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Cart findFirstAndChangeStatus(Cart.Status oldStatus, Cart.Status newStatus) {
         return this.mongoTemplate.findAndModify(new Query(
                where("status").is(oldStatus.name())),
                new Update().set("status", newStatus.name()), Cart.class);
    }
}
