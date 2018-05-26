package ar.com.garbarino.cart;

import ar.com.garbarino.AbstractIntegrationTest;
import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.domain.Item;
import ar.com.garbarino.domain.Product;
import ar.com.garbarino.repository.CartRepository;
import ar.com.garbarino.repository.ProductRepository;
import ar.com.garbarino.repository.TraceRepository;
import ar.com.garbarino.task.ProcessCartTask;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alejandro on 26/05/18.
 */
public class ProcessCartTaskTest extends AbstractIntegrationTest{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TraceRepository traceRepository;

    Set<String> productIds;

    @Before
    public void setup() {
        this.productIds = new HashSet<>();
        this.productIds.add(this.productRepository.save(new Product("TV LG 52'' 4K", 35000d, 50l)).getId());
        this.productIds.add(this.productRepository.save(new Product("SAMSUNG VR", 2000d, 50l)).getId());
        this.productIds.add(this.productRepository.save(new Product("Heladera", 18000d, 50l)).getId());
    }

    @Test
    public void processOneCartOneProduct() throws Exception {

        // Create Carts
        Cart cart = new Cart("Task Test", "tast@test.com");
        cart = this.cartRepository.save(cart);
        cart.addProduct(new Item(cart, this.productRepository.findById(this.productIds.iterator().next()).get(), 2l, 20000d));
        cart.setStatus(Cart.Status.READY);
        cart = this.cartRepository.save(cart);

        // Proccess payment
        ProcessCartTask task = new ProcessCartTask(cartRepository, productRepository, traceRepository);
        task.run();

        Assert.assertEquals("El estado del Cart no es PROCESSED", Cart.Status.PROCESSED, this.cartRepository.findById(cart.getId()).get().getStatus());
        Assert.assertEquals("El total de logs no es 2 (PROCESSING, PROCESSED)", 2, this.traceRepository.count());
    }


    @Test
    public void processThreeCartsOneProduct() throws Exception {

        Product product = this.productRepository.findById(this.productIds.iterator().next()).get();

        // Create Carts
        Cart cart = new Cart("Task Test", "tast@test.com");
        cart = this.cartRepository.save(cart);
        cart.addProduct(new Item(cart, product, 20l, 20000d));
        cart.setStatus(Cart.Status.READY);
        cart = this.cartRepository.save(cart);

        // Proccess payment
        ProcessCartTask task = new ProcessCartTask(cartRepository, productRepository, traceRepository);
        task.run();

        Assert.assertEquals("El estado del Cart no es PROCESSED", Cart.Status.PROCESSED, this.cartRepository.findById(cart.getId()).get().getStatus());
        Assert.assertEquals("El total de logs no es 2 (PROCESSING, PROCESSED)", 2, this.traceRepository.count());

        Cart cart2 = new Cart("Task Test2", "tast@test.com");
        cart2 = this.cartRepository.save(cart2);
        cart2.addProduct(new Item(cart2, product, 20l, 20000d));
        cart2.setStatus(Cart.Status.READY);
        cart2 = this.cartRepository.save(cart2);

        // Proccess payment
        task.run();

        Assert.assertEquals("El estado del Cart no es PROCESSED", Cart.Status.PROCESSED, this.cartRepository.findById(cart2.getId()).get().getStatus());
        Assert.assertEquals("El total de logs no es 4 (PROCESSING, PROCESSED, PROCESSING, PROCESSED)", 4, this.traceRepository.count());


        Cart cart3 = new Cart("Task Test2", "tast@test.com");
        cart3 = this.cartRepository.save(cart3);
        cart3.addProduct(new Item(cart3, product, 20l, 20000d));
        cart3.setStatus(Cart.Status.READY);
        cart3 = this.cartRepository.save(cart3);

        // Proccess payment
        task.run();

        Assert.assertEquals("El estado del Cart no es FAILED", Cart.Status.FAILED, this.cartRepository.findById(cart3.getId()).get().getStatus());
        Assert.assertEquals("El total de logs no es 6 (PROCESSING, PROCESSED, PROCESSING, PROCESSED, PROCESSING, FAILED)", 6, this.traceRepository.count());

    }

    @After
    public void clear(){
        this.cartRepository.deleteAll();
        this.productRepository.deleteAll();
        this.traceRepository.deleteAll();
    }
}
