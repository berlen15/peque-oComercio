package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.CestaDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.exception.ArticuloException;
import com.example.comercio.comercioApp.exception.CestaException;
import com.example.comercio.comercioApp.exception.ErrorResponse;
import com.example.comercio.comercioApp.exception.UsuarioException;
import com.example.comercio.comercioApp.service.impl.ArticuloServiceImpl;
import com.example.comercio.comercioApp.service.impl.CestaServiceImpl;
import com.example.comercio.comercioApp.service.impl.UsuarioServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class CestaController {
    @Autowired
    private CestaServiceImpl cestaService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ArticuloServiceImpl articuloService;

    @ApiOperation(value = "verCesta", notes = "Endpoint que nos permite visualizar la cesta de un usuario dado su nombre de usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se ha podido recuperar la cesta del usuario"),
            @ApiResponse(code = 404, message = "Not found. El usuario no existe")})
    @ExceptionHandler (value = {UsuarioException.class})
    @GetMapping("/cesta")
    public ResponseEntity verCesta(@RequestParam String nombreUsuario) throws UsuarioException {
        try{
            CestaDTO cestaDTO = cestaService.verCesta(nombreUsuario);
            return new ResponseEntity(cestaDTO, HttpStatus.OK);
        } catch(UsuarioException e){
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, "/cesta",
                    "El usuario no existe", LocalDate.now());
            return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "añadirArticulo",
            notes = "Endpoint que nos permite añadir artículos a la cesta de un usuario dado su nombre de usuario \n" +
            "    * y la referencia del artículo que se desea añadir.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se ha podido añadir el artículo a la cesta"),
            @ApiResponse(code = 404, message = "Not found. El articulo no existe"),
            @ApiResponse(code = 400, message = "Bad Request. El articulo no tiene stock")})
    @ExceptionHandler (value = {ArticuloException.class})
    @PostMapping("/cesta/añadirArticulo/{referencia}")
    public ResponseEntity añadirArticulo(@RequestParam String nombreUsuario, @PathVariable String referencia)
    throws ArticuloException {
        try{
            boolean añadir = cestaService.añadirArticulo(nombreUsuario, referencia);
            if(añadir){
                return new ResponseEntity("Se ha añadido el artículo a la cesta",HttpStatus.OK);
            } else {
                return new ResponseEntity("El artículo no tiene stock en estos momentos",HttpStatus.BAD_REQUEST);
            }
        }catch (ArticuloException e){
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, "/cesta/añadirArticulo/"+referencia,
                    "El artículo no existe", LocalDate.now());
            return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "realizarCompra",
            notes = "Endpoint que premite realizar el pago de los artículos de una cesta de un usuario, dado su\n" +
                    "     * numero de tarjeta para realizar el pago.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se ha realizado la compra con éxito"),
            @ApiResponse(code = 400, message = "Bad Request. No se especifica el número e tarjeta o" +
                    "no existen artículos en la cesta")})
    @ExceptionHandler (value = {CestaException.class})
    @PostMapping("/cesta/realizarCompra")
    public ResponseEntity realizarCompra(@RequestParam String nombreUsuario, @RequestBody String numTarjeta) throws
            CestaException {
        try{
            if(numTarjeta == null || numTarjeta.isEmpty())
                return new ResponseEntity("Debe introducir una tarjeta válida para realizar el pago",HttpStatus.BAD_REQUEST);
            cestaService.realizarCompra(nombreUsuario, numTarjeta);
            return new ResponseEntity("El pago se ha realizado correctamente",HttpStatus.OK);
        } catch (CestaException e){
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "/cesta/realizarCompra",
                    "No se han encontrado artículos dentro de la cesta para comprar", LocalDate.now());
            return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
