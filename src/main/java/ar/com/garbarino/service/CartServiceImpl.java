package ar.com.garbarino.service;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.domain.Item;
import ar.com.garbarino.domain.Product;
import ar.com.garbarino.dto.CartClientDto;
import ar.com.garbarino.dto.CartProductDto;
import ar.com.garbarino.dto.ItemDTO;
import ar.com.garbarino.exception.CartStatusViolationException;
import ar.com.garbarino.exception.DomainEntityNotFound;
import ar.com.garbarino.repository.CartRepository;
import ar.com.garbarino.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.HttpResource;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by alejandro on 23/04/18.
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart save(@Valid CartClientDto dto) {
        return cartRepository.save(new Cart(dto.getFullname(), dto.getEmail()));
    }

    @Override
    public Cart save(@Valid Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void addProduct(String id, @Valid CartProductDto cartProductDto) {
        Cart cart = this.cartRepository.findById(id).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Cart con id: " + id));
        Product product = this.productRepository.findById(cartProductDto.getId()).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Producto con id: " + cartProductDto.getId()));

        checkCartStatus(cart);

        cart.addProduct(new Item(cart, product, cartProductDto.getQuantity(), cartProductDto.getUnitPrice()));
        cartRepository.save(cart);
    }

    @Override
    public void deleteProduct(String id, String productId) {
        Cart cart = this.cartRepository.findById(id).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Cart con id: " + id));

        checkCartStatus(cart);

        cart.deleteProduct(productId);
        cartRepository.save(cart);
    }

    @Override
    public List<ItemDTO> getProducts(String id) {
        Cart cart = this.cartRepository.findById(id).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Cart con id: " + id));

        List<ItemDTO> items = cart.getProducts().stream().map(p ->
                new ItemDTO(p.getProduct().getId(),
                        p.getProduct().getDescription(),
                        p.getQuantity(),
                        p.getUnitPrice())
        ).collect(Collectors.toList());

        return items;
    }

    @Override
    public Cart getById(String id){
        return this.cartRepository.findById(id).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Cart con id: " + id));
    }

    @Override
    public void checkout(String id) {
        Cart cart = this.cartRepository.findById(id).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Cart con id: " + id));

        checkCartStatus(cart);

        cart.setStatus(Cart.Status.READY);
        this.cartRepository.save(cart);
    }

    private void checkCartStatus(Cart cart){
        if(!cart.getStatus().equals(Cart.Status.NEW)){
            throw new CartStatusViolationException("El cart (id: " + cart.getId() + " no se encuentra en estado 'NEW' (state: " + cart.getStatus().name() + ")" );
        }
    }
}
