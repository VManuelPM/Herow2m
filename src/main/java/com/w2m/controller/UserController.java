package com.w2m.controller;

import com.w2m.dto.UserDto;
import com.w2m.utils.ApplicationConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@Tag(name = ApplicationConstants.USER_TITLE, description = ApplicationConstants.USER_DESCRIPTION)
public class UserController {
  @PostMapping("/login")
  public void fakeLogin(@RequestBody UserDto userDto) {
    throw new IllegalStateException(
        "This method shouldn't be called. It's implemented by Spring Security filters.");
  }

  @PostMapping("/logout")
  public void fakeLogout() {
    throw new IllegalStateException(
        "This method shouldn't be called. It's implemented by Spring Security filters.");
  }
}
