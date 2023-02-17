package com.example.comercio.comercioApp.service;

import com.example.comercio.comercioApp.dto.ArticuloDTO;

import java.util.List;

public interface ArticuloServiceInterface {
    public List<ArticuloDTO> obtenerDisponibles();

    public ArticuloDTO obtenerArticulo(String referencia);
}
