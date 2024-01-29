package com.application.spring.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.application.spring.controllers.UserController;
import com.application.spring.dto.UserDTO;
import com.application.spring.dto.UserSessionDTO;
import com.application.spring.services.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllUsers() throws Exception {
        // Given
        UserDTO user = new UserDTO("Test", true, null, new UserSessionDTO("testSession"));

        // When
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        // Perform the GET request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Test"))
                .andExpect(jsonPath("$[0].session").exists())
                .andExpect(jsonPath("$[0].session.sessionToken").value("testSession"));
    }

    @Test
    public void getUserBySession() throws Exception {
        UserDTO user = new UserDTO("Test", true, null, new UserSessionDTO("testSession"));

        when(userService.getUserBySessionId("testSession")).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/testSession")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.session").exists())
                .andExpect(jsonPath("$.session.sessionToken").value("testSession"));
    }

    @Test
    public void createAndUpdateUser() throws Exception {
        // Given
        UserDTO userToCreate = new UserDTO("TestUser", true, null, new UserSessionDTO("testSession"));
        UserDTO userToUpdate = new UserDTO("UpdatedTest", false, null, new UserSessionDTO("testSession"));

        // When: Create the user initially
        when(userService.createUser(userToCreate)).thenReturn(userToCreate);
        when(userService.updateUser(userToUpdate, "testSession")).thenReturn(userToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userToCreate)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.name").value("TestUser"));

        // When: Update the user
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/testSession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userToUpdate)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedTest"))
                .andExpect(jsonPath("$.terms").value(false));

    }
}