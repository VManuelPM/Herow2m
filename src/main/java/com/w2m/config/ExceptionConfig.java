package com.w2m.config;

import com.w2m.config.exception.BadRequestException;
import com.w2m.config.exception.NotFoundException;
import com.w2m.dto.MessageDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionConfig {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<MessageDto> notFoundException(Exception e) {
    return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({BadRequestException.class, DataIntegrityViolationException.class})
  public ResponseEntity<MessageDto> badRequestException(Exception e) {
    return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
