package com.example.comercio.comercioApp.serviceTest;

import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.repository.IArticuloRepository;
import com.example.comercio.comercioApp.service.impl.ArticuloServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class ArticuloServiceImplTest {
    @Mock
    private IArticuloRepository articuloRepository;

    @InjectMocks
    private ArticuloServiceImpl articuloService;

    List<Articulo> disponibles;
    Articulo articulo_1;
    Articulo articulo_2;
    Articulo articulo_3;
    @Before
    public void setUp(){
        Articulo articulo_1 = new Articulo(1, "producto_1",
                19.0, new ArrayList<>(), 10, "Referencia_1");

        Articulo articulo_2 = new Articulo(2, "producto_2",
                19.0, new ArrayList<>(), 20, "Referencia_2");

        Articulo articulo_3 = new Articulo(3, "producto_3",
                19.0, new ArrayList<>(), 30, "Referencia_3");

        disponibles = new ArrayList<>();
        disponibles.add(articulo_1);
        disponibles.add(articulo_2);
        disponibles.add(articulo_3);
    }

    @Test
    public void obtenerDisponiblesStockMayorQueCeroTest(){
        Mockito.when(articuloRepository.findByStockGreaterThan(0)).thenReturn(disponibles);

        Assert.assertEquals(articuloService.obtenerDisponibles().size(), 3);
        Assert.assertNotEquals(articuloService.obtenerDisponibles().size(), 0);
    }

    @Test
    public void obtenerDisponiblesStockVacioTest(){
        Mockito.when(articuloRepository.findByStockGreaterThan(0)).thenReturn(null);

        Assert.assertNotEquals(articuloService.obtenerDisponibles().size(), 3);
        Assert.assertTrue(articuloService.obtenerDisponibles().size()==0);
    }

    @Test
    public void obtenerArticuloExistenteTest(){
        Articulo articulo = new Articulo(3, "producto_4",
                19.0, new ArrayList<>(), 30, "Referencia_4");

        Mockito.when(articuloRepository.findByReferencia("Referencia_4")).thenReturn(articulo);

        Assert.assertTrue(articuloService.obtenerArticulo("Referencia_4").getIdArticulo() == 3);
        Assert.assertTrue(articuloService.obtenerArticulo("Referencia_4").getDescripcion().equals("producto_4"));
        Assert.assertEquals(articuloService.obtenerArticulo("Referencia_4").getStock(), 30);
    }

    @Test
    public void obtenerArticuloNoExistenteTest(){
        Mockito.when(articuloRepository.findByReferencia("Referencia_4")).thenReturn(null);

        Assert.assertTrue(articuloService.obtenerArticulo("Referencia_4")==null);
    }
}
