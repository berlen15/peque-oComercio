package com.example.comercio.comercioApp.serviceTest;

import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Usuario;
import com.example.comercio.comercioApp.entity.Venta;
import com.example.comercio.comercioApp.exception.ArticuloException;
import com.example.comercio.comercioApp.repository.IVentaRepository;
import com.example.comercio.comercioApp.service.impl.VentaServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class VentaServiceImplTest {

    @Mock
    private IVentaRepository ventaRepository;

    @InjectMocks
    private VentaServiceImpl ventaService;

    List<Venta> vendidos;
    Venta venta1;
    Venta venta2;
    @Before
    public void setUp(){
        final Articulo articulo1 = new Articulo(1, "producto_1",
                19.0, new ArrayList<>(), 10, "Referencia_1");

        final Usuario usuario = new Usuario (1,"belen", "belen@prueba.com", "Belén");

        vendidos = new ArrayList<>();

        venta1 = new Venta(1, LocalDate.now(), articulo1, 16.9, usuario, "TARJ1");
        venta2 = new Venta(1, LocalDate.now(), articulo1, 16.9, usuario, "TARJ2");

        vendidos.add(venta1);
        vendidos.add(venta2);
    }

    @Test
    public void obtenerMasVendidosUltimaSemanaTest(){
        LocalDate ultimaSemana = LocalDate.now().minusDays(7);

        Mockito.when(ventaRepository.findByFechaGreaterThan(ultimaSemana)).thenReturn(vendidos);

        Assert.assertEquals(ventaService.masVendidosUltimaSemana().size(), 2);
    }

    @Test
    public void obtenerMasVendidosUltimaSemanaVacioTest(){
        Mockito.when(ventaRepository.findByFechaGreaterThan(Mockito.any(LocalDate.class))).thenReturn(null);

        Assert.assertThrows(ArticuloException.class, () -> ventaService.masVendidosUltimaSemana());
    }
}
