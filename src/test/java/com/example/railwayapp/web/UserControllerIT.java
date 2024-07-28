package com.example.railwayapp.web;

import com.example.railwayapp.model.dto.UserInfoDto;
import com.example.railwayapp.model.dto.UserRegisterDto;
import com.example.railwayapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail("user@example.com");
        userInfoDto.setVisitedStations(Collections.singletonList("Station1"));

        Mockito.when(userService.findUserInfoByEmail("user@example.com"))
                .thenReturn(userInfoDto);
    }

    @Test
    public void testRegisterGet() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("registerData"));
    }

    @Test
    public void testProcessRegister() throws Exception {
        mockMvc.perform(post("/register")
                        .param("email", "user@example.com")
                        .param("username", "username")
                        .param("password", "password123")
                        .param("confirmPassword", "password123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        Mockito.verify(userService, Mockito.times(1)).registerUser(Mockito.any(UserRegisterDto.class));
    }

    @Test
    public void testProcessRegisterPasswordsNotMatching() throws Exception {
        mockMvc.perform(post("/register")
                        .param("email", "user@example.com")
                        .param("username", "username")
                        .param("password", "password123")
                        .param("confirmPassword", "password456")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"));
    }

    @Test
    public void testLoginGet() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("loginData"));
    }




    @Test
    @WithMockUser(username = "admin@example.com", roles = {"ADMIN"})
    public void testAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/all-users"))
                .andExpect(status().isOk())
                .andExpect(view().name("profiles"))
                .andExpect(model().attributeExists("users"));
    }
}
