package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.service.impl.ArticuloServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticuloController {
    @Autowired
    private ArticuloServiceImpl articuloService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/articulos/disponibles")
    public List<ArticuloDTO> articulosDisponibles(){
        List<ArticuloDTO> articulosDTO = new ArrayList<>();
        this.articuloService.obtenerDisponibles().stream().forEach(
                (final Articulo articulo) -> articulosDTO.add(modelMapper.map(articulo, ArticuloDTO.class)));

        return articulosDTO;
    }
}
