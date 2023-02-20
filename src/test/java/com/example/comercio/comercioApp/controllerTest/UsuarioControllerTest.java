package com.example.comercio.comercioApp.controllerTest;

import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.exception.UsuarioException;
import com.example.comercio.comercioApp.service.impl.UsuarioServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
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

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UsuarioControllerTest {
    @MockBean
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private MockMvc mockMvc;

    UsuarioDTO usuarioDTO;

    @Before
    public void setUp(){
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(4);
        usuarioDTO.setUsername("belen_test");
    }

    @Test
    public void verPerfilExistente() throws Exception {
        Mockito.when(usuarioService.buscarUsuario(Mockito.any(String.class))).thenReturn(usuarioDTO);

        this.mockMvc.perform((get("/usuario/perfil")
                .param("nombreUsuario", "belen_test")))
                .andExpect(status().isOk());

    }

    @Test
    public void verPerfilNoExistente() throws Exception {
       // doReturn(null).when(usuarioService).buscarUsuario("belen_noexiste");
        Mockito.when(usuarioService.buscarUsuario(Mockito.any(String.class))).thenThrow(UsuarioException.class);

        this.mockMvc.perform((get("/usuario/perfil")
                .param("nombreUsuario", "belen_noexiste")))
                .andExpect(status().isNotFound());

    }
}
