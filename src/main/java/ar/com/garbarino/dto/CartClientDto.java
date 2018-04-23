package ar.com.garbarino.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by alejandro on 23/04/18.
 */
public class CartClientDto {
    @ApiModelProperty(notes = "El nombre y apellido del cliente", required = true)
    @NotNull
    private String fullname;

    @ApiModelProperty(notes = "El email del cliente", required = true)
    @NotNull
    private String email;

    public CartClientDto(@NotNull String fullname, @NotNull String email) {
        this.fullname = fullname;
        this.email = email;
    }

    public CartClientDto(){
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
}
