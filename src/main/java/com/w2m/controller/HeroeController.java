package com.w2m.controller;

import com.w2m.config.exception.BadRequestException;
import com.w2m.config.exception.NotFoundException;
import com.w2m.controller.advice.TrackExecutionTime;
import com.w2m.dto.HeroeDto;
import com.w2m.dto.MessageDto;
import com.w2m.entity.HeroeEntity;
import com.w2m.security.SecurityConstants;
import com.w2m.service.HeroeService;
import com.w2m.utils.ApplicationConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = ApplicationConstants.HEROE_TITLE, description = ApplicationConstants.HEROE_DESCRIPTION)
@SecurityRequirement(name = SecurityConstants.SWAGGER_BEARER_NAME)
public class HeroeController {

  @Autowired HeroeService heroeService;
  private HeroeEntity heroeEntity;
  private final Logger logger = LoggerFactory.getLogger(HeroeController.class);

  @Operation(summary = ApplicationConstants.HEROE_GET)
  @GetMapping()
  @TrackExecutionTime
  public ResponseEntity<List<HeroeEntity>> getHeroes() {
    List<HeroeEntity> heroes = heroeService.getHeroes();
    if(heroes.isEmpty())
      throw new NotFoundException("Heroes not found");
    return new ResponseEntity<>(heroes, HttpStatus.OK);
  }

  @Operation(summary = ApplicationConstants.HEROE_BY_ID)
  @GetMapping("/{idHeroe}")
  @TrackExecutionTime
  public ResponseEntity<HeroeEntity> getHeroeById(@PathVariable("idHeroe") int idHeroe) {
    if (!heroeService.existByIdHeroe(idHeroe)) throw new NotFoundException("Heroe not found");
    heroeService.getHeroe(idHeroe).ifPresent(obj -> heroeEntity = obj);
    return new ResponseEntity(heroeEntity, HttpStatus.OK);
  }

  @Operation(summary = ApplicationConstants.HEROE_BY_NAME)
  @GetMapping("/getName/{heroeName}")
  @TrackExecutionTime
  public ResponseEntity<List<HeroeEntity>> getHeroeByName(
      @PathVariable("heroeName") String heroeName) {
    if(heroeName.isEmpty() || heroeName.isBlank())
      throw new BadRequestException("Heroe name is required");
    if (!heroeService.existByHeroeNameContains(StringUtils.capitalize(heroeName)))
      throw new NotFoundException("Heroe not found");
    List<HeroeEntity> heroes = heroeService.getHeroeByName(heroeName).get();
    return new ResponseEntity(heroes, HttpStatus.OK);
  }

  @Operation(summary = ApplicationConstants.SAVE_HEROE)
  @PostMapping()
  @TrackExecutionTime
  public ResponseEntity<?> saveHeroe(@RequestBody HeroeDto heroeDto) {
    if (StringUtils.isBlank(heroeDto.getHeroeName()))
      throw new NotFoundException("The heroe name is required");
    if (heroeService.existByHeroeName(StringUtils.capitalize(heroeDto.getHeroeName())))
      throw new BadRequestException("There is a heroe with the same name");
    heroeEntity = new HeroeEntity(heroeDto.getHeroeName());
    heroeService.saveHeroe(heroeEntity);
    return new ResponseEntity(new MessageDto("Heroe Created"), HttpStatus.CREATED);
  }

  @Operation(summary = ApplicationConstants.UPDATE_HEROE)
  @PutMapping("/{idHeroe}")
  @TrackExecutionTime
  public ResponseEntity<?> updateHeroe(
      @PathVariable("idHeroe") int idHeroe, @RequestBody HeroeDto heroeDto) {

    if (StringUtils.isBlank(heroeDto.getHeroeName()))
      throw new BadRequestException("Heroe name is mandatory");
    if (!heroeService.existByIdHeroe(idHeroe)) throw new NotFoundException("Heroe not found");
    if (heroeService.existByHeroeName(heroeDto.getHeroeName()))
      throw new BadRequestException("There is a heroe with the same name");

    heroeService.getHeroe(idHeroe).ifPresent(obj -> heroeEntity = obj);
    heroeEntity.setHeroeName(heroeDto.getHeroeName());
    heroeService.saveHeroe(heroeEntity);
    return new ResponseEntity(new MessageDto("Heroe updated"), HttpStatus.OK);
  }

  @Operation(summary = ApplicationConstants.DELETE_HEROE)
  @DeleteMapping("/{idHeroe}")
  @TrackExecutionTime
  public ResponseEntity<?> deleteHeroe(@PathVariable("idHeroe") int idHeroe) {
    if (!heroeService.existByIdHeroe(idHeroe)) throw new NotFoundException("Heroe not found");
    heroeService.deleteHeroe(idHeroe);
    return new ResponseEntity(new MessageDto("Heroe deleted"), HttpStatus.OK);
  }
}
