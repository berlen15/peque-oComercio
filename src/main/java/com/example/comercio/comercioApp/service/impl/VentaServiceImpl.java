package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.repository.IVentaRepository;
import com.example.comercio.comercioApp.service.VentaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaServiceInterface {
    @Autowired
    private IVentaRepository ventaRepository;
    @Override
    public List<Articulo> masVendidosUltimaSemana() {

        Calendar c = Calendar.getInstance();
        Date actual = c.getTime();
        c.add(Calendar.DATE, -7);
        Date anterior = c.getTime();

        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(actual);
        String fechaAnterior = formatter.format(anterior);
        System.out.println(fechaActual);
        System.out.println(fechaAnterior);*/


        return ventaRepository.findAllByFechaBetween(actual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                anterior.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    private java.sql.Date transformarFecha(Date fecha){
        long timeInMilliSeconds = fecha.getTime();
        return new java.sql.Date(timeInMilliSeconds);
    }
}
