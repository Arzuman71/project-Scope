package com.projectScope.projectScope.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectsControllerTest {
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
    @WithUserDetails("arzuman.tast@mail.ru")
    void save_Ok() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", "testName");
        objectNode.put("date", String.valueOf(LocalDateTime.now()));
        objectNode.put("deadline", String.valueOf(LocalDateTime.now()));
        try {
            mvc.perform(post("/projects")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectNode.toString()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @WithUserDetails("arzuman.tast@mail.ru")
    void delete_Ok() {

        try {
            mvc.perform(delete("/projects/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    @WithUserDetails("arzuman.tast@mail.ru")
    void findAllByUserId_OkWithoutParam() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/userLeader/projects")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    @WithUserDetails("arzuman.tast@mail.ru")
    void findAllByUserId_Ok() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", "");
        objectNode.put("dateFrom", "2019-10-29T01:15:00");
        objectNode.put("dateTo", "2022-10-29T01:15:00");
        try {
            mvc.perform(MockMvcRequestBuilders
                    .post("/userLeader/projects")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectNode.toString()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}