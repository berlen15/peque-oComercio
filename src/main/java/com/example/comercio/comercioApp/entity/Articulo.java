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
    @Column(name="disponible")
    private String disponible;
    @Column(name="precio")
    private double precio;
    @OneToMany(mappedBy = "articulo")
    //@JoinColumn(name="ventas", updatable = false)
    private List<Venta> ventas;

    public Articulo(int idArticulo, String descripcion,
                    String disponible, double precio, List<Venta> ventas) {
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
        this.disponible = disponible;
        this.precio = precio;
        this.ventas = ventas;
    }

    public Articulo() {}

    public int getCodigoArticulo() {
        return idArticulo;
    }

    public void setCodigoArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}
