package ar.com.garbarino.cart;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.domain.Item;
import ar.com.garbarino.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by alejandro on 22/05/18.
 */
public class CartTests {

    Cart cart;
    Product product;

    @Before
    public void setup() {
        cart = new Cart("Ale Sanchez", "asanchez@test.com");

        product = spy(new Product("S8 test", 20000d, 100l));
        when(product.getId()).thenReturn("99999");
    }

    @Test
    public void createdOk() throws Exception {
        Assert.assertNotNull("No se cargó el nombre completo", cart.getFullname());
        Assert.assertNotNull("No se cargó el email", cart.getEmail());
        Assert.assertNotNull("No se cargó la fecha de creación", cart.getCreationDate());
        Assert.assertEquals("El estado no es 'NEW'", Cart.Status.NEW, cart.getStatus());
        Assert.assertEquals("El total no es 0.00", new Double(0), cart.getTotal());
        Assert.assertNotNull("La lista de productos es nula", cart.getProducts());
        Assert.assertEquals("La cantidad de productos no es 0", 0, cart.getProducts().size());
    }

    @Test
    public void addProduct() throws Exception {
        double price = 20000;
        long quantity = 1;

        cart.addProduct(new Item(cart, product, quantity, price));

        Assert.assertEquals("La cantidad de productos no es " + quantity, quantity, cart.getProducts().size());
        Assert.assertEquals("El total no es " + (price*quantity), new Double(price*quantity), cart.getTotal());
    }

    @Test
    public void addProductTwice() throws Exception {
        double price = 20000;
        long quantity = 1;

        cart.addProduct(new Item(cart, product, quantity, price)); // agrego una vez
        cart.addProduct(new Item(cart, product, quantity, price)); // agrego otra vez

        Assert.assertEquals("La cantidad de productos no es " + quantity, quantity, cart.getProducts().size());
        Assert.assertEquals("El total no es " + (price*quantity*2), new Double(price*quantity*2), cart.getTotal());
    }

    @Test
    public void removeProduct() throws Exception {
        double price = 20000;
        long quantity = 1;

        cart.addProduct(new Item(cart, product, quantity, price));
        cart.deleteProduct(product.getId());

        Assert.assertEquals("La cantidad de productos no es 0", 0, cart.getProducts().size());
        Assert.assertEquals("El total no es 0.00", new Double(0), cart.getTotal());
    }


    @Test
    public void addProductTwiceThenRemove() throws Exception {
        double price = 20000;
        long quantity = 1;

        cart.addProduct(new Item(cart, product, quantity, price)); // agrego una vez
        cart.addProduct(new Item(cart, product, quantity, price)); // agrego otra vez
        cart.deleteProduct(product.getId());

        Assert.assertEquals("La cantidad de productos no es 0", 0, cart.getProducts().size());
        Assert.assertEquals("El total no es 0.00", new Double(0), cart.getTotal());
    }

}