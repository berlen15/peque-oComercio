package com.example.comercio.comercioApp.controllerTest;

import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.service.impl.UsuarioServiceImpl;
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

    @Test
    public void verPerfilExistente() throws Exception {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1);
        usuarioDTO.setUsername("belen");

        Mockito.when(usuarioService.buscarUsuario(Mockito.any(String.class))).thenReturn(usuarioDTO);

        this.mockMvc.perform((get("/usuario/perfil")
                .param("nombreUsuario", "belen")))
                .andExpect(status().isOk());

    }

    @Test
    public void verPerfilNoExistente() throws Exception {

        Mockito.when(usuarioService.buscarUsuario(Mockito.any(String.class))).thenReturn(null);

        this.mockMvc.perform((get("/usuario/perfil")
                .param("nombreUsuario", "belen")))
                .andExpect(status().isBadRequest());

    }
}
