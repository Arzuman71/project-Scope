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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc
class UserProjectsControllerTest {
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
    void addUserInProjects_Ok() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("projectsId", 1);
        objectNode.put("userId", 8);

        try {
            mvc.perform(post("/projects/user/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectNode.toString()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithUserDetails("test.ok@mail.ru")
    void findAllByUserId() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", "");
        objectNode.put("dateFrom", "2019-10-29T01:15:00");
        objectNode.put("dateTo", "2022-10-29T01:15:00");
        try {
            mvc.perform(post("/userMember/projects")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectNode.toString()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}