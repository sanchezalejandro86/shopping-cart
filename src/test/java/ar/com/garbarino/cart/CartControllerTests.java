package ar.com.garbarino.cart;

import ar.com.garbarino.AbstractIntegrationTest;
import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.domain.Product;
import ar.com.garbarino.dto.CartProductDto;
import ar.com.garbarino.repository.CartRepository;
import ar.com.garbarino.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alejandro on 23/04/18.
 */
public class CartControllerTests extends AbstractIntegrationTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    @Before
    public void setup() throws Exception {
        //Insert initial values
        this.cartRepository.save(new Cart("Juan Perez", "jperez@mail.com"));

        this.productRepository.save(new Product("TV LG 52'' 4K", 35000d, 500l));
        this.productRepository.save(new Product("SAMSUNG VR", 2000d, 300l));
        this.productRepository.save(new Product("TV 52'' 4K", 35000d, 500l));
        this.productRepository.save(new Product("TV SAMSUNG 55'' 4K", 38000d, 500l));
        this.productRepository.save(new Product("TV SAMSUNG 50'' FHD", 30000d, 500l));
        this.productRepository.save(new Product("TV SAMSUNG 45'' FHD", 27000d, 400l));
        this.productRepository.save(new Product("TV SAMSUNG 32'' FHD", 21000d, 500l));
        this.productRepository.save(new Product("TV LG '' 4K", 35000d, 500l));

    }

    @After
    public void clean() {
        this.cartRepository.deleteAll();
    }

    @Test
    public void createAnEmptyOne() throws Exception {
        Cart cart = new Cart("Alejandro Sánchez", "sanchezalejandro86@gmail.com");
        String cartJson = json(cart);

        this.mockMvc.perform(
                post("/carts")
                        .contentType(contentType).content(cartJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.fullname", is(cart.getFullname())))
                .andExpect(jsonPath("$.email", is(cart.getEmail())))
                .andExpect(jsonPath("$.creationDate", notNullValue()))
                .andExpect(jsonPath("$.total", is(0D)))
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products", hasSize(0)))
                .andExpect(jsonPath("$.status", is(Cart.Status.NEW.name())))
        ;
    }

    @Test
    public void addProductToCart() throws Exception {

        Cart cart = this.cartRepository.findAll().get(0); //Juan Perez
        Product product = this.productRepository.findAll().get(0); //"TV LG 52'' 4K" | 35000d | 500

        String cartProductJson = json(new CartProductDto(product.getId(), 1L, product.getUnitPrice()));

        this.mockMvc.perform(
                post("/carts/" + cart.getId() + "/products")
                        .contentType(contentType).content(cartProductJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void removeProductFromCart() throws Exception {

        Cart cart = this.cartRepository.findAll().get(0); //Juan Perez

        // Add product 1
        Product product = this.productRepository.findAll().get(0); //"TV LG 52'' 4K" | 35000d | 500

        String cartProductJson = json(new CartProductDto(product.getId(), 1L, product.getUnitPrice()));

        this.mockMvc.perform(
                post("/carts/" + cart.getId() + "/products")
                        .contentType(contentType).content(cartProductJson));

        // Remove product 1
        this.mockMvc.perform(
                delete("/carts/" + cart.getId() + "/products/" + product.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsFromCart() throws Exception {
        Cart cart = this.cartRepository.findAll().get(0); //Juan Perez

        // Add product 1
        Product product1 = this.productRepository.findAll().get(0);
        String cartProductJson = json(new CartProductDto(product1.getId(), 1L, product1.getUnitPrice()));
        this.mockMvc.perform(
                post("/carts/" + cart.getId() + "/products")
                        .contentType(contentType).content(cartProductJson));

        // Add product 2
        Product product2 = this.productRepository.findAll().get(1);
        cartProductJson = json(new CartProductDto(product2.getId(), 2L, product2.getUnitPrice()));
        this.mockMvc.perform(
                post("/carts/" + cart.getId() + "/products")
                        .contentType(contentType).content(cartProductJson));


        // Get Products
        this.mockMvc.perform(
                get("/carts/" + cart.getId() + "/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$.[0].id", is(product1.getId())))
                .andExpect(jsonPath("$.[0].description", is(product1.getDescription())))
                .andExpect(jsonPath("$.[0].quantity", is(1)))
                .andExpect(jsonPath("$.[0].unitPrice", is(product1.getUnitPrice())))

                .andExpect(jsonPath("$.[1].id", is(product2.getId())))
                .andExpect(jsonPath("$.[1].description", is(product2.getDescription())))
                .andExpect(jsonPath("$.[1].quantity", is(2)))
                .andExpect(jsonPath("$.[1].unitPrice", is(product2.getUnitPrice())))
        ;
    }

    @Test
    public void getCompleteCart() throws Exception {
        Cart cart = this.cartRepository.findAll().get(0); //Juan Perez


        // Add product 1
        Product product1 = this.productRepository.findAll().get(0);
        String cartProductJson = json(new CartProductDto(product1.getId(), 1L, product1.getUnitPrice()));
        this.mockMvc.perform(
                post("/carts/" + cart.getId() + "/products")
                        .contentType(contentType).content(cartProductJson));

        // Add product 2
        Product product2 = this.productRepository.findAll().get(1);
        cartProductJson = json(new CartProductDto(product2.getId(), 2L, product2.getUnitPrice()));
        this.mockMvc.perform(
                post("/carts/" + cart.getId() + "/products")
                        .contentType(contentType).content(cartProductJson));


        // Get Products
        this.mockMvc.perform(
                get("/carts/" + cart.getId()))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.total", is(product1.getUnitPrice() * 1 + product2.getUnitPrice() * 2)))
                .andExpect(jsonPath("$.products", hasSize(2)))

                .andExpect(jsonPath("$.products[0].product.id", is(product1.getId())))

                .andExpect(jsonPath("$.products[1].product.id", is(product2.getId())))
        ;
    }

    @Test
    public void checkoutCart() throws Exception {
        Cart cart = this.cartRepository.findAll().get(0); //Juan Perez

        // Add product 1
        Product product1 = this.productRepository.findAll().get(0);
        String cartProductJson = json(new CartProductDto(product1.getId(), 1L, product1.getUnitPrice()));
        this.mockMvc.perform(
                post("/carts/" + cart.getId() + "/products")
                        .contentType(contentType).content(cartProductJson));

        // Add product 2
        Product product2 = this.productRepository.findAll().get(1);
        cartProductJson = json(new CartProductDto(product2.getId(), 2L, product2.getUnitPrice()));
        this.mockMvc.perform(
                post("/carts/" + cart.getId() + "/products")
                        .contentType(contentType).content(cartProductJson));

        // Checkout
        this.mockMvc.perform(
                post("/carts/" + cart.getId() + "/checkout"))
                .andExpect(status().isOk());

        // Checkout again must fail
        this.mockMvc.perform(
                post("/carts/" + cart.getId() + "/checkout"))
                .andExpect(status().isForbidden());

    }
}