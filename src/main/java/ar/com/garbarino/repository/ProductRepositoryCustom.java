package ar.com.garbarino.repository;

import ar.com.garbarino.domain.Product;

/**
 * Created by alejandro on 23/05/18.
 */
public interface ProductRepositoryCustom {
    Product discountStock(String id, long quantity);
}
