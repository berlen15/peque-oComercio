package com.example.comercio.comercioApp.service;

import com.example.comercio.comercioApp.dto.ArticuloDTO;

import java.util.List;

public interface VentaServiceInterface {
    public List<ArticuloDTO> masVendidosUltimaSemana();
}
