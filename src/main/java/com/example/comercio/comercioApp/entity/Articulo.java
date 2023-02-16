package com.example.comercio.comercioApp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="articulo")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idarticulo")
    private Integer idArticulo;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="precio")
    private double precio;

    @Column(name="stock")
    private int stock;

    @OneToMany(mappedBy = "articulo")
    private List<Venta> ventas;

    public Articulo(int idArticulo, String descripcion,
                    double precio, List<Venta> ventas, int stock) {
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ventas = ventas;
        this.stock = stock;
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
}
