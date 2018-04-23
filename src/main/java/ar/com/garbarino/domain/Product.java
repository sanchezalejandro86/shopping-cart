package ar.com.garbarino.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by alejandro on 23/04/18.
 */
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @NotNull
    private String description;

    @NotNull
    private Double unit_price;

    private Long stock;

    public Product(@NotNull String description, @NotNull Double unit_price, Long stock) {
        this.description = description;
        this.unit_price = unit_price;
        this.stock = stock;
    }

    public Product(){
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}