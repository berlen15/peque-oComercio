package com.example.comercio.comercioApp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ArticuloDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7222227040494239629L;
    @ApiModelProperty(hidden = true)
    private int idArticulo;
    @ApiModelProperty(position = 0)
    private String descripcion;
    @ApiModelProperty(position = 1)
    private double precio;
    @ApiModelProperty(position = 2)
    @JsonBackReference
    private List<VentaDTO> ventas;
    @ApiModelProperty(position = 3)
    private int stock;
    @ApiModelProperty(position = 4)
    private String referencia;
    @ApiModelProperty(position = 5)
    @JsonBackReference
    private List<UsuarioDTO> usuariosCompradores;

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<VentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaDTO> ventas) {
        this.ventas = ventas;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<UsuarioDTO> getUsuariosCompradores() {
        return usuariosCompradores;
    }

    public void setUsuariosCompradores(List<UsuarioDTO> usuariosCompradores) {
        this.usuariosCompradores = usuariosCompradores;
    }
}