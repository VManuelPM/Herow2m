package com.w2m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.w2m.dto.MessageDto;
import com.w2m.entity.HeroeEntity;
import com.w2m.repository.HeroeRepository;
import com.w2m.security.JWTAuthenticationFilter;
import com.w2m.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

// Test de Integraciòn
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HeroeControllerTest {

  @MockBean private HeroeRepository heroeRepository;

  @Autowired private TestRestTemplate restTemplate;

  private HeroeEntity heroe;
  private HttpHeaders headers;
  private HttpEntity httpEntity;
  protected String token;
  protected JWTAuthenticationFilter jwtAuthenticationFilter;

  public HeroeControllerTest() {
    heroe = new HeroeEntity(TestConstants.HEROE_ID_BD, TestConstants.HEROE_NAME_BD);
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    jwtAuthenticationFilter = new JWTAuthenticationFilter();
    token = jwtAuthenticationFilter.createToken(TestConstants.USERNAME);
    headers.set(TestConstants.AUTH, "Bearer " + token);
    httpEntity = new HttpEntity(headers);
  }

  @BeforeEach
  void setUp() {}

  @Test
  @Order(1)
  void saveHeroe() throws JsonProcessingException {
    Map<String, String> map = new HashMap<>();
    map.put("heroeName", TestConstants.HEROE_NAME_BD);
    ObjectMapper objectMapper = new ObjectMapper();
    HttpEntity<?> request = new HttpEntity(objectMapper.writeValueAsString(map), headers);
    // when
    ResponseEntity<HeroeEntity> heroeEntityResponse =
        restTemplate.postForEntity("/", request, HeroeEntity.class);
    // then
    assertThat(heroeEntityResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  @Test
  @Order(2)
  void getHeroes() {
    List<HeroeEntity> heroes = Arrays.asList(heroe);
    // given
    BDDMockito.given(heroeRepository.findAll()).willReturn(heroes);
    // when
    ResponseEntity<HeroeEntity[]> heroeEntityResponse =
        restTemplate.exchange("/", HttpMethod.GET, httpEntity, HeroeEntity[].class);
    // then
    assertThat(heroeEntityResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  @Order(3)
  void putHeroeById() throws JsonProcessingException {
    Map<String, String> map = new HashMap<>();
    map.put("heroeName", TestConstants.HEROE_NAME);
    ObjectMapper objectMapper = new ObjectMapper();
    HttpEntity<?> request = new HttpEntity(objectMapper.writeValueAsString(map), headers);
    // when
    ResponseEntity<HeroeEntity> heroeEntityResponse =
        restTemplate.exchange(
            "/{idHeroe}", HttpMethod.PUT, request, HeroeEntity.class, TestConstants.HEROE_ID_BD);
    // then
    assertThat(heroeEntityResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  @Order(4)
  void deleteHeroeById(){
    HttpEntity<?> request = new HttpEntity(headers);
    // when
    ResponseEntity<HeroeEntity> heroeEntityResponse =
            restTemplate.exchange(
                    "/{idHeroe}", HttpMethod.DELETE, request, HeroeEntity.class, TestConstants.HEROE_ID_BD);
    // then
    assertThat(heroeEntityResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}
