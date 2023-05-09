package com.salon.salon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // get by id

    @DeleteMapping("/delete/{id}")
    public void deleteServiseById(@PathVariable Integer id) {
        serviceService.deleteServiseById(id);
    }

    // update
}
