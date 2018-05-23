package ar.com.garbarino.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by alejandro on 23/04/18.
 */
public class CartProductDto {
    @ApiModelProperty(notes = "ID del producto", required = true)
    @NotNull
    private String id;

    @ApiModelProperty(notes = "La cantidad de productos a agregar", required = true)
    @NotNull
    private Long quantity;

    @ApiModelProperty(notes = "Precio unitario del producto")
    private Double unitPrice;

    public CartProductDto(@NotNull String id, @NotNull Long quantity, Double unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public CartProductDto(){
    }

    public String getId() {
        return id;
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
