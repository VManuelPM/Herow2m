package com.w2m.config.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException() {}

  public NotFoundException(String message) {
    super(message);
  }
}
