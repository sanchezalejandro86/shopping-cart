package ar.com.garbarino.controller;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.dto.CartClientDto;
import ar.com.garbarino.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by alejandro on 23/04/18.
 */
@RestController
@RequestMapping(path = "/carts")
@Api(value="Cart",description = "Servicio de carritos de compra")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @ApiOperation(value = "Crear nuevo Cart", response = Cart.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El Carrito de Compra fue creado exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Cart store(@Valid @RequestBody CartClientDto cartClientDto) {
        return cartService.save(cartClientDto);
    }
}
