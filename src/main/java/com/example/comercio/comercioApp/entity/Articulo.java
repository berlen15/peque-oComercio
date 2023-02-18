package com.example.comercio.comercioApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="articulo")
public class Articulo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idarticulo")
    private Integer idArticulo;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="precio")
    private double precio;

    @Column(name="stock")
    private int stock;

    @OneToMany(mappedBy = "articulo")
    @JsonBackReference
    private List<Venta> ventas;

    @Column(name = "referencia")
    private String referencia;

    @ManyToMany(mappedBy =  "articulosComprados")
    private List<Usuario> usuariosCompradores;

    public Articulo(Integer idArticulo, String descripcion,
                    double precio, List<Venta> ventas, int stock, String referencia, List<Usuario> usuariosCompradores) {
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ventas = ventas;
        this.stock = stock;
        this.referencia = referencia;
        this.usuariosCompradores = usuariosCompradores;
    }

    public Articulo() {}

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

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<Usuario> getUsuariosCompradores() {
        return usuariosCompradores;
    }

    public void setUsuariosCompradores(List<Usuario> usuariosCompradores) {
        this.usuariosCompradores = usuariosCompradores;
    }
}
