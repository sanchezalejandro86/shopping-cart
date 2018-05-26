package ar.com.garbarino.task;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.domain.Item;
import ar.com.garbarino.domain.Product;
import ar.com.garbarino.domain.Trace;
import ar.com.garbarino.exception.InsufficientStockException;
import ar.com.garbarino.repository.CartRepository;
import ar.com.garbarino.repository.ProductRepository;
import ar.com.garbarino.repository.TraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandro on 23/05/18.
 */
@Component
public class ProcessCartTask implements Runnable{

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final TraceRepository traceRepository;

    @Autowired
    public ProcessCartTask(CartRepository cartRepository, ProductRepository productRepository, TraceRepository traceRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.traceRepository = traceRepository;
    }

    @Override
    public void run() {
        Cart cart = this.cartRepository.findFirstAndChangeStatus(Cart.Status.READY, Cart.Status.PROCESSING);

        if(cart == null) return;

        this.traceRepository.save(new Trace(cart, Cart.Status.PROCESSING, "Se comenzó a procesar el carrito"));

        List<Item> proccessingItems = new ArrayList<>();

        try {
            for (Item item : cart.getProducts()) {
                proccessingItems.add(item);

                // product tiene la version anterior a descontar el stock
                Product product = this.productRepository.discountStock(item.getProduct().getId(), item.getQuantity());

                if (product.getStock() < item.getQuantity()) {
                    throw new InsufficientStockException("No hay stock suficiente para el producto: " + product.getId() + " [ " + product.getStock() + " < " + item.getQuantity() + "]");
                }
            }

            cart.setStatus(Cart.Status.PROCESSED);
            this.traceRepository.save(new Trace(cart, Cart.Status.PROCESSED, "Se procesó el carrito exitosamente"));

        }catch (InsufficientStockException e){
            cart.setStatus(Cart.Status.FAILED);
            for (Item i : proccessingItems) {
                this.productRepository.discountStock(i.getProduct().getId(), -i.getQuantity()); // "rollback" con la cantidad anterior
            }
            this.traceRepository.save(new Trace(cart, Cart.Status.FAILED, e.getMessage()));
        }finally {
            this.cartRepository.save(cart);
        }
    }
}
