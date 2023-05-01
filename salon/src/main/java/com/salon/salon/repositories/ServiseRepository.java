package com.salon.salon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Servise;

public interface ServiseRepository extends JpaRepository<Servise, Integer> {
    
}
