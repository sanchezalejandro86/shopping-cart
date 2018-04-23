package ar.com.garbarino.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandro on 23/04/18.
 */
@Document(collection = "carts")
public class Cart {

    @Id
    private String id;

    @ApiModelProperty(notes = "El nombre y apellido del cliente", required = true)
    @NotNull
    private String fullname;

    @ApiModelProperty(notes = "El email del cliente", required = true)
    @NotNull
    private String email;

    private LocalDateTime creationDate;

    @NotNull
    private List<Product> products;

    @NotNull
    private Double total;

    @NotNull
    private Status status;

    public enum Status {
        NEW,

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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