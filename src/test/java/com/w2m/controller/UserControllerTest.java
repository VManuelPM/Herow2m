package com.w2m.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w2m.dto.UserDto;
import com.w2m.entity.UserEntity;
import com.w2m.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

  @Autowired private WebApplicationContext context;

  private MockMvc mvc;

  private UserDto user;

  public UserControllerTest() {
    user = new UserDto();
    user.setUsername("admin");
    user.setPassword("password");
  }

  @BeforeEach
  public void setup() {
    mvc =
        MockMvcBuilders.webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
  }

  @Test
  void fakeLogin() throws Exception {
    Map<String, String> map = new HashMap<>();
    map.put("username", "a");
    map.put("password", "a");
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(map);

    mvc.perform(
            MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
       // .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }
}
