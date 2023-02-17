package com.example.comercio.comercioApp.dto;


import java.io.Serializable;
import java.util.List;

public class UsuarioDTO implements Serializable {
    private Integer id;
    private String username;
    private String email;
    private String nombre;
    private List<ArticuloDTO> articulosComprados;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ArticuloDTO> getArticulosComprados() {
        return articulosComprados;
    }

    public void setArticulosComprados(List<ArticuloDTO> articulosComprados) {
        this.articulosComprados = articulosComprados;
    }
}
