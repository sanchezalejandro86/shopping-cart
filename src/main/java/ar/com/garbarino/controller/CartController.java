package ar.com.garbarino.controller;

import ar.com.garbarino.domain.Cart;
import ar.com.garbarino.dto.CartClientDto;
import ar.com.garbarino.dto.CartProductDto;
import ar.com.garbarino.dto.ItemDTO;
import ar.com.garbarino.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public Cart create(@Valid @RequestBody CartClientDto cartClientDto) {
        return this.cartService.save(cartClientDto);
    }

    @ApiOperation(value = "Crear nuevo Cart", response = Cart.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El Producto fue agregado al carrito de compra exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/{id}/products", method = RequestMethod.POST)
    public void addProduct(@PathVariable String id, @Valid @RequestBody CartProductDto cartProductDto) {
        this.cartService.addProduct(id, cartProductDto);
    }

    @ApiOperation(value = "Eliminar Producto del Cart", response = Cart.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El Producto fue agregado al carrito de compra exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{cartId}/products/{productId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String cartId, @PathVariable String productId) {
        this.cartService.deleteProduct(cartId, productId);
    }

    @ApiOperation(value = "Obtener los productos del Cart", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El listado de productos fue retornado exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{cartId}/products", method = RequestMethod.GET)
    public List<ItemDTO> getProducts(@PathVariable String cartId) {
        return this.cartService.getProducts(cartId);
    }

    @ApiOperation(value = "Obtener el Cart completo", response = Cart.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El Cart fue retornado exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{cartId}", method = RequestMethod.GET)
    public Cart getById(@PathVariable String cartId) {
        return this.cartService.getById(cartId);
    }

}
