package ar.com.garbarino.service;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.dto.CartClientDto;
import ar.com.garbarino.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * Created by alejandro on 23/04/18.
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart save(@Valid CartClientDto dto) {
        return cartRepository.save(new Cart(dto.getFullname(), dto.getEmail()));
    }

    @Override
    public Cart save(@Valid Cart cart) {
        return cartRepository.save(cart);
    }
}
