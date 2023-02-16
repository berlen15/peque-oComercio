package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.service.impl.VentaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VentaController {
    @Autowired
    VentaServiceImpl ventaService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/articulos/masVendidos")
    public List<ArticuloDTO> articulosMasVendidos(){
        List<ArticuloDTO> articulosDTO = new ArrayList<>();
        this.ventaService.masVendidosUltimaSemana().stream().forEach(
                (final Articulo articulo) -> articulosDTO.add(modelMapper.map(articulo, ArticuloDTO.class)));

        System.out.println(articulosDTO);
        return articulosDTO;
    }
}
