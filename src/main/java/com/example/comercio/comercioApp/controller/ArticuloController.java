package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.exception.ArticuloException;
import com.example.comercio.comercioApp.exception.ErrorResponse;
import com.example.comercio.comercioApp.service.impl.ArticuloServiceImpl;
import com.example.comercio.comercioApp.service.impl.VentaServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ArticuloController {
    @Autowired
    private ArticuloServiceImpl articuloService;

    @Autowired
    VentaServiceImpl ventaService;

    @ApiOperation(value = "articulosDisponibles", notes = "Endpoint para obtener los artículos cuyo stock sea mayor que cero (disponibles)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Existen articulos cuyo stock es mayor que cero"),
            @ApiResponse(code = 404, message = "Not found. No se han encontrado articulos disponibles")})
    @ExceptionHandler(value = {ArticuloException.class})
    @GetMapping("/articulos/disponibles")
    public ResponseEntity articulosDisponibles() throws ArticuloException {
        try{
            List<ArticuloDTO> articulos = this.articuloService.obtenerDisponibles();
            return new ResponseEntity(articulos, HttpStatus.OK);
        } catch (ArticuloException e){
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, "/articulos/disponibles",
                    "No hay artículos disponibles", LocalDate.now());
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "articulosMasVendidos",
            notes = "Endpoint para obtener los artículos con más ventas en los últimos siete días")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Existen articulos en el top de ventas"),
            @ApiResponse(code = 404, message = "Not found. No se han encontrado articulos en el top de ventas")})
    @GetMapping("/articulos/topVentas")
    @ExceptionHandler (value = {ArticuloException.class})
    public ResponseEntity articulosMasVendidos() throws ArticuloException {
        try {
            List<ArticuloDTO> articulos = this.ventaService.masVendidosUltimaSemana();
            return new ResponseEntity(articulos, HttpStatus.OK);
        }catch (ArticuloException e){
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, "/articulos/topVentas",
                    "No hay artículos en el top de ventas", LocalDate.now());
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }
    }
}
