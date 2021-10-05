package com.w2m.controller;

import com.w2m.dto.MessageDto;
import com.w2m.entity.HeroeEntity;
import com.w2m.repository.HeroeRepository;
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

  public HeroeControllerTest() {
    heroe = new HeroeEntity(TestConstants.HEROE_ID_BD, TestConstants.HEROE_NAME_BD);
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", TestConstants.TOKEN_TEST);
    httpEntity = new HttpEntity(headers);
  }

  @BeforeEach
  void setUp() {}

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
  @Order(1)
  void saveHeroe() {
    Map<String, String> map = new HashMap<>();
    map.put("heroeName", "Spiderman");
    HttpEntity<?> request = new HttpEntity(map, headers);
    // when
    ResponseEntity<HeroeEntity> heroeEntityResponse =
        restTemplate.postForEntity("/", request, HeroeEntity.class);
    // then
    assertThat(heroeEntityResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }
}
