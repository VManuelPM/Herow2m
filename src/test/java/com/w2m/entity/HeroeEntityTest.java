package com.w2m.entity;

import com.w2m.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroeEntityTest {

  private HeroeEntity heroe;

  @BeforeEach
  void setUp() {
    heroe = new HeroeEntity();
  }

  @Test
  void getId() {
    heroe.setId(TestConstants.HEROE_ID);
    assertEquals(TestConstants.HEROE_ID, heroe.getId());
  }

  @Test
  void getHeroeName() {
    heroe.setHeroeName(TestConstants.HEROE_NAME);
    assertEquals(TestConstants.HEROE_NAME, heroe.getHeroeName());
  }

  @Test
  void constructWithParams() {
    heroe = new HeroeEntity(TestConstants.HEROE_NAME);
    assertEquals(TestConstants.HEROE_NAME, heroe.getHeroeName());
  }

}
