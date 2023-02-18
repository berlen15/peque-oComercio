package com.example.comercio.comercioApp.repository;

import com.example.comercio.comercioApp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer> {
    //List<Venta> findByFechaGreaterThan(LocalDate ultima);
    List<Venta> findByFechaGreaterThan(LocalDate ultima);

}
