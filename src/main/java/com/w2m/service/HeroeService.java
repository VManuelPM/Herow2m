package com.w2m.service;

import com.w2m.config.exception.NotFoundException;
import com.w2m.entity.HeroeEntity;
import com.w2m.repository.HeroeRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HeroeService {

  private Logger logger = LoggerFactory.getLogger(HeroeService.class);

  @Autowired HeroeRepository heroeRepository;

  public List<HeroeEntity> getHeroes() {
    return heroeRepository.findAll();
  }

  @Cacheable(cacheNames = "heroes", key = "#id", sync = true)
  public Optional<HeroeEntity> getHeroe(int id) {
    logger.info("Cache not using");
    return heroeRepository.findById(id);
  }

  public Optional<List<HeroeEntity>> getHeroeByName(String name) {
    return heroeRepository.findByHeroeNameContainingIgnoreCase(name);
  }

  @CachePut(cacheNames = "heroes", key = "#heroe.id")
  public void saveHeroe(HeroeEntity heroe) {
    heroeRepository.save(heroe);
  }

  @CacheEvict(cacheNames = "heroes", key = "#id")
  public void deleteHeroe(int id) {
    heroeRepository.deleteById(id);
  }

  public boolean existByHeroeName(String heroeName) {
    return heroeRepository.existsByHeroeName(heroeName);
  }

  public boolean existByHeroeNameContains(String heroeName) {
    return heroeRepository.existsByHeroeNameContaining(heroeName);
  }

  public boolean existByIdHeroe(int idHeroe) {
    return heroeRepository.existsById(idHeroe);
  }
}
