package com.projectScope.projectScope.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.projectScope.projectScope.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {

        mvc = webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void auth_Ok() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("email", "arzuman.kochoyan98@mail.ru");
        objectNode.put("password", "Arzuman");

        try {
            mvc.perform(MockMvcRequestBuilders.post("/user/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectNode.toString()))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void auth_ClientError() throws Exception {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("email", "an.kochoyan@mail.ru");
        objectNode.put("password", "A");

        mvc.perform(MockMvcRequestBuilders.post("/user/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    void register_OKNotProfilePicture() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", "Arzuman");
        objectNode.put("surname", "Kochoyan");
        objectNode.put("password", "Arzuman");
        objectNode.put("email", "arzuman.tast@mail.ru");
        objectNode.put("type", "TEAM_MEMBER");

        try {
            mvc.perform(post("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectNode.toString()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void register_OK() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", "Arzuman");
        objectNode.put("surname", "Kochoyan");
        objectNode.put("password", "Arzuman");
        objectNode.put("email", "arzuman.kochoyan98@mail.ru");
        objectNode.put("type", "TEAM_MEMBER");
        File f = new File("C:\\Users\\Arzuman\\Desktop\\Folder\\rest\\projectScope\\upload\\7.jpg");
        try {
            InputStream inputStream = new FileInputStream(f);
            MockMultipartFile file = new MockMultipartFile("file", "7.jpg",
                    MediaType.IMAGE_PNG_VALUE, inputStream);

            mvc.perform(multipart("/user").file(file)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectNode.toString()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void register_ClientError() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", "Arzuman");
        objectNode.put("surname", "Kochoyan");
        objectNode.put("password", "Arzuman");
        objectNode.put("email", "ClientError");
        objectNode.put("type", "TEAM_MEMBER");
        File f = new File("C:\\Users\\Arzuman\\Desktop\\Folder\\rest\\projectScope\\upload\\7.jpg");
        try {
            InputStream inputStream = new FileInputStream(f);
            MockMultipartFile file = new MockMultipartFile("file", "7.jpg",
                    MediaType.IMAGE_PNG_VALUE, inputStream);

            mvc.perform(multipart("/user").file(file)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectNode.toString()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void activate_Ok() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/user/activate?email=arzuman.kochoyan98@mail.ru&otp=0eace02f-9e10-4106-a01f-86a9550f01b3")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void activate_ClientError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/activate?email=arzuman.kochoyan98@mail.ru&otp=ClientError")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("arzuman.tast@mail.ru")
    void users() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/users")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}