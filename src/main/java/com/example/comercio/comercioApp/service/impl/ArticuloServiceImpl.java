package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.repository.IArticuloRepository;
import com.example.comercio.comercioApp.service.ArticuloServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticuloServiceImpl implements ArticuloServiceInterface {
    @Autowired
    private IArticuloRepository articuloRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<ArticuloDTO> obtenerDisponibles() {
        List<ArticuloDTO> articulos = new ArrayList<>();
        articuloRepository.findAll().stream().forEach((final Articulo articulo) -> {
            if(articulo.getStock()>0) articulos.add(modelMapper.map(articulo, ArticuloDTO.class));
        });
        return articulos;
    }

    @Override
    public ArticuloDTO obtenerArticulo(String referencia) {
        return modelMapper.map(articuloRepository.findByReferencia(referencia), ArticuloDTO.class);
    }
}
