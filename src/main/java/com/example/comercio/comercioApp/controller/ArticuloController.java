package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.service.impl.ArticuloServiceImpl;
import com.example.comercio.comercioApp.service.impl.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ArticuloController {
    @Autowired
    private ArticuloServiceImpl articuloService;

    @Autowired
    VentaServiceImpl ventaService;

    @GetMapping("/articulos/disponibles")
    public List<ArticuloDTO> articulosDisponibles(){
        return this.articuloService.obtenerDisponibles();
    }

    @GetMapping("/articulos/topVentas")
    public Set<ArticuloDTO> articulosMasVendidos(){
       return this.ventaService.masVendidosUltimaSemana();
    }
}
