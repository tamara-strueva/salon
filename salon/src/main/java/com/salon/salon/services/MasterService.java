package com.salon.salon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salon.salon.models.Master;
import com.salon.salon.repositories.MasterRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MasterService {
    @Autowired
    private MasterRepository masterRepository;

    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }

    public void saveMaster(Master master) {
        masterRepository.save(master);
    }

    public void deleteMasterById(Integer id) {
        masterRepository.deleteById(id);
    }

    public Master getMasterById(Integer id) {
        return masterRepository.findById(id).get();
    }

    public List<Master> getMastersByName(String name) {
        return masterRepository.findByFirstName(name);
    }

    public List<Master> getMastersBySpeciality(String speciality) {
        return masterRepository.findBySpeciality(speciality);
    }
    
}
