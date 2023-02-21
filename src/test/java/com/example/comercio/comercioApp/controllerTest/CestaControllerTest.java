package com.example.comercio.comercioApp.controllerTest;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.dto.CestaDTO;
import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.exception.CestaException;
import com.example.comercio.comercioApp.exception.UsuarioException;
import com.example.comercio.comercioApp.repository.IArticuloRepository;
import com.example.comercio.comercioApp.service.impl.ArticuloServiceImpl;
import com.example.comercio.comercioApp.service.impl.CestaServiceImpl;
import com.example.comercio.comercioApp.service.impl.UsuarioServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CestaControllerTest {
    @MockBean
    private CestaServiceImpl cestaService;

    @MockBean
    private UsuarioServiceImpl usuarioService;

    @MockBean
    private ArticuloServiceImpl articuloService;

    @MockBean
    private IArticuloRepository articuloRepository;

    @Autowired
    private MockMvc mockMvc;

    ModelMapper modelMapper = new ModelMapper();

    UsuarioDTO usuarioDTO;
    ArticuloDTO articuloDTO;
    Articulo articulo;

    CestaDTO cestaDTO;

    CestaDTO cestaDTO2;

    Articulo articulo2;

    ArticuloDTO articuloDTO2;
    @Before
    public void setUp(){
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1);
        usuarioDTO.setUsername("belen");

        cestaDTO = new CestaDTO();
        cestaDTO.setUsuario(usuarioDTO);

        articulo = new Articulo();
        articulo.setIdArticulo(1);
        articulo.setReferencia("REF1");
        articulo.setStock(4);

        articuloDTO = modelMapper.map(articulo, ArticuloDTO.class);

        cestaDTO2 = new CestaDTO();
        cestaDTO2.setUsuario(usuarioDTO);
        cestaDTO2.setListadoArticulos(new ArrayList<>());
        cestaDTO2.getListadoArticulos().add(articuloDTO);

        articulo2 = new Articulo();
        articulo2.setIdArticulo(2);
        articulo2.setReferencia("REF2");
        articulo2.setStock(0);

        articuloDTO2 = modelMapper.map(articulo2, ArticuloDTO.class);
    }

    @Test
    public void verCestaExistenteTest() throws Exception {
        Mockito.when(usuarioService.buscarUsuario("belen")).thenReturn(this.usuarioDTO);

        Mockito.when(cestaService.verCesta(Mockito.any(String.class))).thenReturn(this.cestaDTO);

        this.mockMvc.perform((get("/cesta")
                        .param("nombreUsuario", "belen")))
                .andExpect(status().isOk());

    }

    @Test
    public void verCestaNoExistenteTest() throws Exception {
        Mockito.when(cestaService.verCesta(Mockito.any(String.class))).thenThrow(UsuarioException.class);

        this.mockMvc.perform((get("/cesta")
                        .param("nombreUsuario", "belen")))
                .andExpect(status().isNotFound());

    }
    @Test
    public void añadirArticuloSinInformarReferencia() throws Exception {
        doReturn(false).when(cestaService).realizarCompra(Mockito.any(String.class), Mockito.any(String.class));

        mockMvc.perform(post("/cesta/añadirArticulo/{referencia}", "REF1")
                        .contentType("application/json")
                        .param("nombreUsuario", "belen"))
                    .andExpect(status().isBadRequest());
    }



    @Test
    public void añadirArticuloExistente() throws Exception {
       doReturn(articuloDTO).when(articuloService).obtenerArticulo(Mockito.any(String.class));
       doReturn(true).when(cestaService).añadirArticulo(Mockito.any(String.class), Mockito.any(String.class));

        mockMvc.perform(post("/cesta/añadirArticulo/{referencia}", "REF1")
                        .param("nombreUsuario", "belen"))
                .andExpect(status().isOk());
    }

    @Test
    public void añadirArticuloSinStock() throws Exception {
        doReturn(articuloDTO2).when(articuloService).obtenerArticulo(Mockito.any(String.class));

        mockMvc.perform(post("/cesta/añadirArticulo/{referencia}", "REF2")
                        .contentType("application/json")
                        .param("nombreUsuario", "belen"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void realizarCompraConExito() throws Exception {
        Mockito.when(cestaService.realizarCompra(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
        String requestBody = "{\"numTarjeta\":\"Tarjeta\"}";
        mockMvc.perform(post("/cesta/realizarCompra")
                        .contentType("application/json")
                        .content(requestBody)
                        .param("nombreUsuario", "belen"))
                .andExpect(status().isOk());
    }

    @Test
    public void realizarCompraSinNumTarjeta() throws Exception {
        Mockito.when(cestaService.realizarCompra(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
        mockMvc.perform(post("/cesta/realizarCompra")
                        .contentType("application/json")
                        .param("nombreUsuario", "belen"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void realizarCompraCestaVacia() throws Exception {
       // Mockito.when(cestaService.realizarCompra(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(false);

        Mockito.when(cestaService.realizarCompra(Mockito.any(String.class),
                Mockito.any(String.class))).thenThrow(CestaException.class);
        String requestBody = "{\"numTarjeta\":\"Tarjeta\"}";
        mockMvc.perform(post("/cesta/realizarCompra")
                        .contentType("application/json")
                        .content(requestBody)
                        .param("nombreUsuario", "belen"))
                .andExpect(status().isBadRequest());
    }

}
