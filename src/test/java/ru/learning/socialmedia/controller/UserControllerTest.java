package ru.learning.socialmedia.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.model.request.UserRegistrationRequest;
import ru.learning.socialmedia.model.response.UserInSearchResponse;
import ru.learning.socialmedia.model.response.UserLogInResponse;
import ru.learning.socialmedia.service.UserService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserFullInformation userFullInformation;
    private UserLogInResponse userLogInResponse;
    private UserInSearchResponse userInSearchResponse;

    @BeforeEach
    void setUp() {
        userFullInformation = new UserFullInformation();
        userLogInResponse = new UserLogInResponse();
        userInSearchResponse = new UserInSearchResponse();
    }

    @Test
    void getAllUsersTest() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(List.of(userFullInformation));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    void addUserTest() throws Exception {
        Mockito.when(userService.createUser(any(UserRegistrationRequest.class))).thenReturn(userLogInResponse);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void getUserByIdTest() throws Exception {
        Mockito.when(userService.getUserById(anyLong())).thenReturn(userFullInformation);

        mockMvc.perform(get("/api/v1/users/user")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void getUserByIdNotFoundTest() throws Exception {
        Mockito.when(userService.getUserById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/api/v1/users/user")
                        .param("id", "0"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"error\":\"User not found\"}"));
    }

    @Test
    void searchUserByNameTest() throws Exception {
        Mockito.when(userService.searchUsersByName(anyString())).thenReturn(List.of(userInSearchResponse));

        mockMvc.perform(get("/api/v1/users/searchByName")
                        .param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    void searchUserByNameNotFoundTest() throws Exception {
        Mockito.when(userService.searchUsersByName(anyString())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/users/searchByName")
                        .param("name", "John"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"error\":\"Users not found by name: John\"}"));
    }
}