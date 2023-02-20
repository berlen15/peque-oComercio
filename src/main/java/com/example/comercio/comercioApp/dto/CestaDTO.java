package com.example.comercio.comercioApp.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class CestaDTO implements Serializable {
    @ApiModelProperty(hidden = true)
    private Integer idcesta;
    @ApiModelProperty(position = 0)
    private List<ArticuloDTO> listadoArticulos;
    @ApiModelProperty(position = 1)
    private UsuarioDTO usuario;

    public Integer getIdcesta() {
        return idcesta;
    }

    public void setIdcesta(Integer idcesta) {
        this.idcesta = idcesta;
    }

    public List<ArticuloDTO> getListadoArticulos() {
        return listadoArticulos;
    }

    public void setListadoArticulos(List<ArticuloDTO> listadoArticulos) {
        this.listadoArticulos = listadoArticulos;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}

