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
import com.salon.salon.models.Servise;
import com.salon.salon.services.ServiseService;

@RestController
@RequestMapping("/services")
public class ServiseController {
    @Autowired
    private ServiseService serviceService;

    @PostMapping("/add")
    public void saveServise(@RequestBody Servise servise) {
        serviceService.saveServise(servise);
    }

    @GetMapping("/get")
    public List<Servise> getAllServisesList() {
        return serviceService.getAllServises();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Servise> getServiseById(@PathVariable Integer id) {
        try{
            Servise servise = serviceService.getServiseById(id);
            return ResponseEntity.ok(servise);
        } catch (NoSuchElementException exeption) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Service with ID: " + id + " doesn't exist...");
        }   
    }

    @GetMapping("/getn/{name}")
    public List<Servise> getServisesByName(@PathVariable String name) {
        return serviceService.getServisesByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteServiseById(@PathVariable Integer id) {
        serviceService.deleteServiseById(id);
    }

    // update
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateClient(@RequestBody Servise servise, @PathVariable Integer id) {
        try {
            // Client baseClient = clientService.getClientById(id);
            // baseClient.updateClient(client);
            // clientService.saveClient(baseClient);
            Servise baseServise = serviceService.getServiseById(id);
            baseServise.updateServise(servise);
            serviceService.saveServise(baseServise);
            return ResponseEntity.ok(baseServise);
        } catch(NoSuchElementException exception){
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Service with ID: " + id + " doesn't exist...");
        } 
    }
}
