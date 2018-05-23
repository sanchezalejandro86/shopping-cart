package ar.com.garbarino.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by alejandro on 23/04/18.
 */
@Document(collection = "products")
public class Product {

    @ApiModelProperty(notes = "Id del producto")
    @Id
    private String id;

    @ApiModelProperty(notes = "Nombre del producto", required = true)
    @NotNull
    private String description;

    @ApiModelProperty(notes = "Precio unitario del producto", required = true)
    @NotNull
    private Double unitPrice;

    @ApiModelProperty(notes = "Stock del producto", required = true)
    @NotNull
    private Long stock;

    public Product(@NotNull String description, @NotNull Double unitPrice, @NotNull Long stock) {
        this.description = description;
        this.unitPrice = unitPrice;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unit_price) {
        this.unitPrice = unitPrice;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}