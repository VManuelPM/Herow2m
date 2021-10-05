package com.w2m.repository;

import com.w2m.entity.HeroeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface HeroeRepository extends JpaRepository<HeroeEntity, Integer> {

    Optional<List<HeroeEntity>> findByHeroeNameContainingIgnoreCase(String heroeName);

    boolean existsByHeroeName(String heroeName);

    boolean existsByHeroeNameContaining(String heroeName);
}
