package com.w2m.dto;

import com.w2m.utils.TestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

  private UserDto userDto;

  public UserDtoTest() {
    userDto = new UserDto();
  }

  @Test
  void getUsername() {
    userDto.setUsername(TestConstants.HEROE_NAME);
    assertEquals(TestConstants.HEROE_NAME, userDto.getUsername());
  }

  @Test
  void getPassword() {
    userDto.setPassword(TestConstants.HEROE_NAME);
    assertEquals(TestConstants.HEROE_NAME, userDto.getPassword());
  }
}
