package ar.com.garbarino.service;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.dto.CartClientDto;

import javax.validation.Valid;

/**
 * Created by alejandro on 23/04/18.
 */
public interface CartService {
    Cart save(@Valid CartClientDto cartClientDto);

    Cart save(@Valid Cart cart);
}
