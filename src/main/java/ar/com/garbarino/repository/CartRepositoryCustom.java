package ar.com.garbarino.repository;

import ar.com.garbarino.domain.Cart;

/**
 * Created by alejandro on 23/05/18.
 */
public interface CartRepositoryCustom {
    Cart findFirstAndChangeStatus(Cart.Status oldStatus, Cart.Status newStatus);
}
