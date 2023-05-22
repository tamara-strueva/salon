package com.salon.salon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salon.salon.models.Servise;
import com.salon.salon.repositories.ServiseRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiseService {
    @Autowired
    private ServiseRepository serviseRepository;

    public List<Servise> getAllServises() {
        return serviseRepository.findAll();
    }

    public void saveServise(Servise servise) {
        serviseRepository.save(servise);
    }

    public void deleteServiseById(Integer id) {
        serviseRepository.deleteById(id);
    }

    public Servise getServiseById(Integer id) {
        return serviseRepository.findById(id).get();
    }

    public List<Servise> getServisesByName(String name) {
        return serviseRepository.findByNameContaining(name);
    }

    // public void insertRelationTable(Order order, Servise servise) {
    //     serviseRepository.insertRelationTable(order.getId(), servise.getId());
    // }
    
}
