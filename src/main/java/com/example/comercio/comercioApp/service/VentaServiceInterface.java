package com.example.comercio.comercioApp.service;

import com.example.comercio.comercioApp.dto.ArticuloDTO;

import java.util.Set;

public interface VentaServiceInterface {
    public Set<ArticuloDTO> masVendidosUltimaSemana();
}
