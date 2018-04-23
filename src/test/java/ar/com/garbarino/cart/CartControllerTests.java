package ar.com.garbarino.cart;

import ar.com.garbarino.AbstractIntegrationTest;
import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.repository.CartRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alejandro on 23/04/18.
 */
public class CartControllerTests extends AbstractIntegrationTest {

    @Autowired
    private CartRepository cartRepository;

    @Before
    public void setup() throws Exception {
        //Insert initial values
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
}
