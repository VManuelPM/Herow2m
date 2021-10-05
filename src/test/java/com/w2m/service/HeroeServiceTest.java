package com.w2m.service;

import com.w2m.entity.HeroeEntity;
import com.w2m.repository.HeroeRepository;
import com.w2m.utils.TestConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class HeroeServiceTest {

  @Mock private HeroeRepository heroeRepository;

  @InjectMocks private HeroeService heroeService;

  private HeroeEntity heroe;

  @BeforeEach
  void setUp() {
    heroe = new HeroeEntity(TestConstants.HEROE_ID, TestConstants.HEROE_NAME);
    heroeService.saveHeroe(heroe);
  }

  @Test
  void shouldGetHeroesSuccesfully() {
    // given
    heroe = new HeroeEntity(TestConstants.HEROE_ID, TestConstants.HEROE_NAME);
    List<HeroeEntity> expectedHeroes = Arrays.asList(heroe);
    Mockito.when(heroeRepository.findAll()).thenReturn(expectedHeroes);

    // when
    List<HeroeEntity> actualHeroes = heroeService.getHeroes();

    // then
    Assertions.assertThat(actualHeroes).isEqualTo(expectedHeroes);
    Mockito.verify(heroeRepository, Mockito.times(1)).findAll();
  }

  @Test
  void shoulSaveHeroe() {
    heroe = new HeroeEntity(TestConstants.HEROE_ID, TestConstants.HEROE_NAME);
    heroeService.saveHeroe(heroe);
    Mockito.verify(heroeRepository, Mockito.times(1)).save(heroe);
  }

  @Test
  void shouldGetHeroe() {
    Mockito.when(heroeRepository.getById(TestConstants.HEROE_ID))
        .thenReturn(new HeroeEntity(TestConstants.HEROE_ID, TestConstants.HEROE_NAME));

    heroeService.getHeroe(TestConstants.HEROE_ID).ifPresent(obj -> heroe = obj);
    assertEquals(TestConstants.HEROE_ID, heroe.getId());
    assertEquals(TestConstants.HEROE_NAME, heroe.getHeroeName());
  }

  @Test
  void shouldGetHeroeByName() {
    heroe = new HeroeEntity(TestConstants.HEROE_ID, TestConstants.HEROE_NAME);
    Optional<List<HeroeEntity>> expectedHeroes = Optional.of(Arrays.asList(heroe));
    Mockito.when(heroeRepository.findByHeroeNameContainingIgnoreCase(TestConstants.HEROE_NAME))
        .thenReturn(expectedHeroes);

    Optional<List<HeroeEntity>> actualHeroes =
        heroeService.getHeroeByName(TestConstants.HEROE_NAME);

    Assertions.assertThat(actualHeroes).isEqualTo(expectedHeroes);
    Mockito.verify(heroeRepository, Mockito.times(1))
        .findByHeroeNameContainingIgnoreCase(TestConstants.HEROE_NAME);
  }

  @Test
  void shouldDeleteHeroe() {
    heroeService.deleteHeroe(TestConstants.HEROE_ID);
    Mockito.verify(heroeRepository, Mockito.times(1)).deleteById(TestConstants.HEROE_ID);
  }

  @Test
  void shouldExistByHeroeName() {
    Mockito.when(heroeRepository.existsByHeroeName(TestConstants.HEROE_NAME)).thenReturn(true);
    Boolean current = heroeService.existByHeroeName(TestConstants.HEROE_NAME);
    assertEquals(true, current);
    Mockito.verify(heroeRepository, Mockito.times(1)).existsByHeroeName(TestConstants.HEROE_NAME);
  }

  @Test
  void shouldExistByHeroeNameContains() {
    Mockito.when(heroeRepository.existsByHeroeNameContaining(TestConstants.HEROE_NAME_2)).thenReturn(true);
    Boolean current = heroeService.existByHeroeNameContains(TestConstants.HEROE_NAME_2);
    assertEquals(true, current);
    Mockito.verify(heroeRepository, Mockito.times(1))
        .existsByHeroeNameContaining(TestConstants.HEROE_NAME_2);
  }

  @Test
  void shouldExistByIdHeroe() {
    Mockito.when(heroeRepository.existsById(TestConstants.HEROE_ID)).thenReturn(true);
    Boolean current = heroeService.existByIdHeroe(TestConstants.HEROE_ID);
    assertEquals(true, current);
    Mockito.verify(heroeRepository, Mockito.times(1)).existsById(TestConstants.HEROE_ID);
  }
}
