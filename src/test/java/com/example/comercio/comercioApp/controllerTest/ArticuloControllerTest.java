package com.example.comercio.comercioApp.controllerTest;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.exception.ArticuloException;
import com.example.comercio.comercioApp.service.impl.ArticuloServiceImpl;
import com.example.comercio.comercioApp.service.impl.VentaServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ArticuloControllerTest {
    @MockBean
    private ArticuloServiceImpl articuloService;

    @MockBean
    private VentaServiceImpl ventaService;

    @Autowired
    private MockMvc mockMvc;

    ArticuloDTO articulo;
    ArticuloDTO articulo2;
    ArticuloDTO articulo3;
    List<ArticuloDTO> articulos;
    List<ArticuloDTO> articulos2;

    @Before
    public void setUp(){
        articulo = new ArticuloDTO();
        articulo.setIdArticulo(43);
        articulos = Arrays.asList(articulo);

        articulo2 = new ArticuloDTO();
        articulo.setIdArticulo(43);

        articulo3 = new ArticuloDTO();
        articulo.setIdArticulo(44);

        articulos2 = Arrays.asList(articulo, articulo2);

    }

    @Test
    public void articulosDisponiblesTest() throws Exception {
        Mockito.when(articuloService.obtenerDisponibles()).thenReturn(articulos);

        this.mockMvc.perform((get("/articulos/disponibles"))).andExpect(
                status().isOk());

    }

    @Test
    public void articulosDisponiblesVacioTest() throws Exception {
        Mockito.when(articuloService.obtenerDisponibles()).thenThrow(ArticuloException.class);

        this.mockMvc.perform((get("/articulos/disponibles"))).andExpect(
                status().isNotFound());

    }

    @Test
    public void articulosmasVendidosTest() throws Exception {
        Mockito.when(ventaService.masVendidosUltimaSemana()).thenReturn(articulos);

        this.mockMvc.perform((get("/articulos/topVentas"))).andExpect(
                status().isOk());

    }

    @Test
    public void articulosmasVendidosVacioTest() throws Exception {
        Mockito.when(ventaService.masVendidosUltimaSemana()).thenThrow(ArticuloException.class);

        this.mockMvc.perform((get("/articulos/topVentas"))).andExpect(
                status().isNotFound());

    }
}
