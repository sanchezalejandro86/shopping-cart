package ar.com.garbarino.dto;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Created by alejandro on 22/05/18.
 */
public class ItemDTO {

    @ApiModelProperty(notes = "Id del producto")
    @Id
    private String id;

    @ApiModelProperty(notes = "Nombre del producto", required = true)
    @NotNull
    private String description;

    @ApiModelProperty(notes = "La cantidad de productos a agregar", required = true)
    @NotNull
    private Long quantity;

    @ApiModelProperty(notes = "Precio unitario del producto", required = true)
    @NotNull
    private Double unitPrice;

    public ItemDTO(String id, @NotNull String description, @NotNull Long quantity, @NotNull Double unitPrice) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
