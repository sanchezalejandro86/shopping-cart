package ar.com.garbarino.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "carts")
public class Cart {

    @Id
    private String id;

    @NotNull
    private String fullname;

    @NotNull
    private String email;

    private LocalDateTime creationDate;

    private List<Product> products;

    private Double total;

    private Status status;

    enum Status {
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

}