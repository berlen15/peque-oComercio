package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.service.impl.ArticuloServiceImpl;
import com.example.comercio.comercioApp.service.impl.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticuloController {
    @Autowired
    private ArticuloServiceImpl articuloService;

    @Autowired
    VentaServiceImpl ventaService;

    //Endpoint para obtener los artículos cuyo stock sea mayor que cero (disponibles)
    @GetMapping("/articulos/disponibles")
    public ResponseEntity articulosDisponibles(){
        List<ArticuloDTO> articulos = this.articuloService.obtenerDisponibles();
        if(articulos != null){
            return new ResponseEntity(articulos, HttpStatus.OK);
        } else {
            return new ResponseEntity("No existen artículos disponibles en este momento", HttpStatus.NOT_FOUND);
        }
    }

    //Endpoint para obtener los artículos con más ventas en la última semana
    @GetMapping("/articulos/topVentas")
    public ResponseEntity articulosMasVendidos(){
        List<ArticuloDTO> articulos = this.ventaService.masVendidosUltimaSemana();
        if(articulos != null){
            return new ResponseEntity(articulos, HttpStatus.OK);
        } else {
            return new ResponseEntity("Ha habido un problema al cargar el listado de top de ventas", HttpStatus.NOT_FOUND);
        }
    }
}
