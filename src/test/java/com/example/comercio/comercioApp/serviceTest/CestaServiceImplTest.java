package com.example.comercio.comercioApp.serviceTest;


import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Cesta;
import com.example.comercio.comercioApp.entity.Usuario;
import com.example.comercio.comercioApp.repository.IArticuloRepository;
import com.example.comercio.comercioApp.repository.ICestaRepository;
import com.example.comercio.comercioApp.repository.IUsuarioRepository;
import com.example.comercio.comercioApp.repository.IVentaRepository;
import com.example.comercio.comercioApp.service.impl.CestaServiceImpl;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class CestaServiceImplTest {

    @Mock
    private ICestaRepository cestaRepository;
    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private IArticuloRepository articuloRepository;

    @Mock
    private IVentaRepository ventaRepository;

    @InjectMocks
    private CestaServiceImpl cestaService;

    Cesta cesta;
    Cesta cesta2;
    Usuario usuario;
    Usuario usuario2;
    Articulo articulo1;
    Articulo articulo2;
    @Before
    public void setUp(){
        usuario = new Usuario (1,"belen", "belen@prueba.com", "Belén");
        usuario2 = new Usuario (1,"belen2", "belen2@prueba.com", "Belén2");


        cesta = new Cesta(usuario);
        cesta2 = new Cesta(usuario2);

        articulo1 = new Articulo(1, "producto_1",
                19.0, new ArrayList<>(), 0, "Referencia_1");
        articulo1.getUsuariosCompradores().add(usuario);

        articulo2 = new Articulo(2, "producto_2",
                19.0, new ArrayList<>(), 20, "Referencia_2");
        articulo2.getUsuariosCompradores().add(usuario);

        usuario2.getArticulosComprados().add(articulo1);
        usuario2.getArticulosComprados().add(articulo2);
    }


    @Test
    public void añadirArticuloConStockTest(){
        Articulo articulo1 = new Articulo(1, "producto_1",
                19.0, new ArrayList<>(), 10, "Referencia_1");

        Mockito.when(usuarioRepository.findByUsername("belen")).thenReturn(usuario);

        Mockito.when(cestaRepository.findByUsuario(usuario)).thenReturn(cesta);

        Mockito.when(articuloRepository.findByReferencia("Referencia_1")).thenReturn(articulo1);

        cestaService.añadirArticulo("belen", "Referencia_1");

        Assert.assertEquals(cesta.getListadoArticulos().size(), 1);
        Assert.assertTrue(articulo1.getStock()==9);
    }

    @Test
    public void añadirArticuloSinStockTest(){
        Articulo articulo = new Articulo(1, "producto_1",
                19.0, new ArrayList<>(), 0, "Referencia_1");

        Mockito.when(usuarioRepository.findByUsername("belen")).thenReturn(usuario);

        Mockito.when(cestaRepository.findByUsuario(usuario)).thenReturn(cesta);

        Mockito.when(articuloRepository.findByReferencia("Referencia_1")).thenReturn(articulo);

        cestaService.añadirArticulo("belen", "Referencia_1");

        Assert.assertEquals(cesta.getListadoArticulos().size(), 0);
        Assert.assertTrue(articulo.getStock()==0);
    }

    @Test
    public void verCestaExistenteTest(){
        Mockito.when(usuarioRepository.findByUsername("belen")).thenReturn(usuario);
        Mockito.when(cestaRepository.findByUsuario(usuario)).thenReturn(cesta);        ;

        Assert.assertTrue(cestaService.verCesta("belen")!=null);
    }

    @Test
    public void verCestaNoExistenteTest(){
        Mockito.when(usuarioRepository.findByUsername("belen")).thenReturn(usuario);
        Mockito.when(cestaRepository.findByUsuario(usuario)).thenReturn(null);

        Assert.assertTrue(cestaService.verCesta("belen")==null);
    }

    @Test
    public void realizarCompraTest(){
        Mockito.when(usuarioRepository.findByUsername("belen2")).thenReturn(usuario);
        Mockito.when(cestaRepository.findByUsuario(usuario)).thenReturn(cesta2);

        Mockito.when(articuloRepository.findByReferencia("Referencia_1")).thenReturn(articulo1);
        Mockito.when(articuloRepository.findByReferencia("Referencia_2")).thenReturn(articulo2);

        cestaService.añadirArticulo("belen2", "Referencia_1");
        cestaService.añadirArticulo("belen2", "Referencia_1");

        cestaService.realizarCompra("belen2", "TARJ_EJEM");

        verify(articuloRepository, times(2)).findByReferencia(Mockito.any(String.class));

        Mockito.when(cestaRepository.findByUsuario(usuario)).thenReturn(null);
        Mockito.when(usuarioRepository.findByUsername("belen2")).thenReturn(usuario2);

        Assert.assertTrue(articuloRepository.findByReferencia("Referencia_1").getUsuariosCompradores().contains(usuario));
        Assert.assertTrue(articuloRepository.findByReferencia("Referencia_2").getUsuariosCompradores().contains(usuario));

        Assert.assertNull(cestaRepository.findByUsuario(usuario));
        Assert.assertTrue(usuarioRepository.findByUsername("belen2").getArticulosComprados().size()==2);

    }
}
