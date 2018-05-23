package ar.com.garbarino.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;

/**
 * Created by alejandro on 23/04/18.
 */
public class Item {

    @ApiModelProperty(notes = "Carrito al que pertenece el item")
    @DBRef
    private Cart cart;

    @ApiModelProperty(notes = "Producto a agregar")
    @DBRef(lazy = true)
    private Product product;

    @ApiModelProperty(notes = "La cantidad de productos a agregar", required = true)
    @NotNull
    private Long quantity;

    @ApiModelProperty(notes = "Precio unitario del producto", required = true)
    @NotNull
    private Double unitPrice;

    public Item(Cart cart, Product product, @NotNull Long quantity, @NotNull Double unitPrice) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Item(){
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
