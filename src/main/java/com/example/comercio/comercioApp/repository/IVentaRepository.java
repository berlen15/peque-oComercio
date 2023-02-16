package com.example.comercio.comercioApp.repository;

import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer> {
    //@Query("SELECT SUM(cantidad) AS sum_cantidad FROM Venta WHERE articulo_id = ?1")
   // @Query(value = "SELECT * FROM Venta WHERE fecha>=?1 AND fecha<=?2",  nativeQuery = true)
   // List<Articulo> obtenerMasVendidosUltimaSemana(String fechaInicio, String fechaFin);
    List<Articulo> findAllByFechaBetween(Date fechaInicio, Date fechaFin);
}
