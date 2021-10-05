package com.w2m.dto;

import com.w2m.utils.TestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroeDtoTest {

  @Test
  void getHeroeName() {
    HeroeDto heroeDto = new HeroeDto();
    heroeDto.setHeroeName(TestConstants.HEROE_NAME);
    assertEquals(TestConstants.HEROE_NAME, heroeDto.getHeroeName());
  }
}
