package ar.com.garbarino.seed;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.domain.Item;
import ar.com.garbarino.domain.Product;
import ar.com.garbarino.repository.CartRepository;
import ar.com.garbarino.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alejandro on 27/05/18.
 */
@Profile("!test")
@Component
public class LocalApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    final Logger logger = Logger.getLogger("LocalApplicationStartup");

    List<Product> products = new ArrayList<>();


    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        logger.info("Starting seed");

        this.createProducts();

        this.createCarts(100);

        logger.info("Seeder finished successfully");
    }

    private void createCarts(int count) {
        logger.info("Carts created:");
        for(int i=1; i<=count; i++){
            Cart cart = new Cart("Mr Seed"+i, "seed"+i+"@fake.com");
            this.cartRepository.save(cart);

            Product p = products.get((int)(Math.random() * products.size()));
            cart.addProduct(new Item(cart, p, 1L, p.getUnitPrice()));

            p = products.get((int)(Math.random() * products.size()));
            cart.addProduct(new Item(cart, p, 3L, p.getUnitPrice()));

            cart.setStatus(Cart.Status.READY);

            this.cartRepository.save(cart);
            logger.info(cart.getId() + " | " + cart.getFullname() + " | " + cart.getEmail() + " | " + cart.getTotal() + " | " + cart.getStatus());
        }
    }

    private void createProducts() {
        logger.info("Products created:");
        products.add(this.productRepository.save(new Product("Smart TV Noblex 49 \" 4K Ultra HD 91DI|49X6500", 13999d, 25l)));
        products.add(this.productRepository.save(new Product("Heladera con Freezer Coventry HF24BGC1 Blanca", 9499d, 5l)));
        products.add(this.productRepository.save(new Product("Celular Libre Huawei GW Dorado", 4499d, 30l)));
        products.add(this.productRepository.save(new Product("Mixer Philips HR1363/06", 1499d, 10l)));
        products.add(this.productRepository.save(new Product("Microondas BGH Quick Chef 28 L B228D Inoxidable", 4399d, 10l)));

        for (Product p : products) {
            logger.info(p.getId() + " | " + p.getDescription() + " | " + p.getUnitPrice() + " | " + p.getStock());
        }
    }

}