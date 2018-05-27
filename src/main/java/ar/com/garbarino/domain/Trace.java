package ar.com.garbarino.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by alejandro on 26/04/18.
 */
public class Trace {


    @ApiModelProperty(notes = "Id del logueo")
    @Id
    private String id;

    @ApiModelProperty(notes = "Carrito al que pertenece el item")
    private Cart cart;

    @ApiModelProperty(notes = "Estado actual del carrito")
    @NotNull
    private Cart.Status status;

    @ApiModelProperty(notes = "Detalle del logueo")
    @NotNull
    private String description;

    @ApiModelProperty(notes = "Fecha de creación del carrito")
    private LocalDateTime creationDate;

    public Trace(Cart cart, @NotNull Cart.Status status, @NotNull String description) {
        this.cart = cart;
        this.status = status;
        this.description = description;
        this.creationDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart.Status getStatus() {
        return status;
    }

    public void setStatus(Cart.Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}