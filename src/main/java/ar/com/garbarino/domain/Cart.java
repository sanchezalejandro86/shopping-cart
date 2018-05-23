package ar.com.garbarino.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by alejandro on 23/04/18.
 */
@Document(collection = "carts")
public class Cart {

    @ApiModelProperty(notes = "Id del carrito")
    @Id
    private String id;

    @ApiModelProperty(notes = "El nombre y apellido del cliente", required = true)
    @NotNull
    private String fullname;

    @ApiModelProperty(notes = "El email del cliente", required = true)
    @NotNull
    private String email;

    @ApiModelProperty(notes = "Fecha de creación del carrito")
    private LocalDateTime creationDate;

    @ApiModelProperty(notes = "Lista de productos que se desean comprar")
    @NotNull
    private List<Item> products;

    @ApiModelProperty(notes = "Precio total a pagar por los productos que haya en el carrito")
    @NotNull
    private Double total;

    @ApiModelProperty(notes = "Estado actual del carrito")
    @NotNull
    private Status status;

    public enum Status {
        NEW, READY,

    }

    public Cart(@NotNull String fullname, @NotNull String email) {
        this.fullname = fullname;
        this.email = email;
        this.creationDate = LocalDateTime.now();
        this.products = new ArrayList<>();
        this.total = 0d;
        this.status = Status.NEW;
    }

    public Cart(){
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<Item> getProducts() {
        return products;
    }

    public void setProducts(List<Item> products) {
        this.products = products;
    }

    public void addProduct(Item item) {
        Optional<Item> existingItem = products.stream().filter((p) -> p.getProduct().getId().equals(item.getProduct().getId())).findFirst();

        if(existingItem.isPresent()){
            existingItem.get().setQuantity(existingItem.get().getQuantity() + item.getQuantity());
        }else{
            this.products.add(item);
        }
        total += item.getUnitPrice() * item.getQuantity();
    }

    public void deleteProduct(String productId) {
        Optional<Item> existingItem = products.stream().filter((p) -> p.getProduct().getId().equals(productId)).findFirst();

        if(existingItem.isPresent()){
            products.remove(existingItem.get());
            total -= existingItem.get().getUnitPrice() * existingItem.get().getQuantity();
        }else{
            // TODO BAD REQUEST
        }
    }

    public Double getTotal() {
        return total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}