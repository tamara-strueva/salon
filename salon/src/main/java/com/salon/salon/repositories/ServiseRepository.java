package com.salon.salon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Servise;

public interface ServiseRepository extends JpaRepository<Servise, Integer> {
    List<Servise> findByNameContaining(String name);

}
