package ar.com.garbarino.service;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.dto.CartClientDto;
import ar.com.garbarino.dto.CartProductDto;
import ar.com.garbarino.dto.ItemDTO;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by alejandro on 23/04/18.
 */
public interface CartService {
    Cart save(@Valid CartClientDto cartClientDto);

    Cart save(@Valid Cart cart);

    void addProduct(String id, @Valid CartProductDto cartProductDto);

    void deleteProduct(String id, String productId);

    List<ItemDTO> getProducts(String cartId);

    Cart getById(String cartId);

    void checkout(String cartId);
}
