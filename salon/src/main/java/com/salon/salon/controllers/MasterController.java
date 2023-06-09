package com.salon.salon.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salon.salon.exceptions.CustomNotFoundExсeption;
import com.salon.salon.models.Master;
import com.salon.salon.services.MasterService;

@RestController
@RequestMapping("/masters")
public class MasterController {
    @Autowired
    private MasterService masterService;

    @PostMapping("/add")
    public void saveMaster(@RequestBody Master master) {
        masterService.saveMaster(master);
    }

    @GetMapping("/get")
    public List<Master> getAllMastersList() {
        return masterService.getAllMasters();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Master> getMasterById(@PathVariable Integer id) {
        try{
            Master master = masterService.getMasterById(id);
            return ResponseEntity.ok(master);
        } catch (NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Master with ID: " + id + "doesn't exist...");
        }
    }

    @GetMapping("/getn/{name}")
    public ResponseEntity<?> getMastersByName(@PathVariable String name) {
        try{
            List<Master> masters = masterService.getMastersByName(name);
            return ResponseEntity.ok(masters);
        } catch (NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Master with NAME: " + name + "doesn't exist...");
        }
    }

    @GetMapping("/getl/{name}")
    public ResponseEntity<?> getMastersByLastName(@PathVariable String name) {
        try{
            List<Master> masters = masterService.getMasterByLastName(name);
            return ResponseEntity.ok(masters);
        } catch (NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Master with LASTNAME: " + name + "doesn't exist...");
        }
    }
    
    @GetMapping("/gets/{speciality}")
    public ResponseEntity<?> getMastersBySpeciality(@PathVariable String speciality) {
        try{
            List<Master> masters = masterService.getMastersBySpeciality(speciality);
            return ResponseEntity.ok(masters);
        } catch (NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Master with SPECIALITY: " + speciality + "doesn't exist...");
        }
    }
 
    @GetMapping("/getns/{name}/{speciality}")
    public ResponseEntity<?> getMastersByNameAndSpeciality(@PathVariable String name, @PathVariable String speciality) {
        try {
            List<Master> masters = masterService.getMastersByNameAndSpeciality(name, speciality);
            return ResponseEntity.ok(masters);
        } catch (NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Master with NAME: " + name + " and SPECIALITY: " + speciality + "doesn't exist...");
        }
    }

    @GetMapping("/getfl/{name}/{lastName}")
    public ResponseEntity<?> getMastersByFullName(@PathVariable String name, @PathVariable String lastName) {
        try {
            List<Master> masters = masterService.getMasterByFirstAndLastName(name, lastName);
            return ResponseEntity.ok(masters);
        } catch (NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Master with NAME: " + name + " " + lastName + "doesn't exist...");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMasterById(@PathVariable Integer id) {
        masterService.deleteMasterById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateClient(@RequestBody Master master, @PathVariable Integer id) {
        try {
            Master baseMaster = masterService.getMasterById(id);
            baseMaster.updateMaster(master);
            masterService.saveMaster(baseMaster);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
    }
}
