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

    @DeleteMapping("/delete/{id}")
    public void deleteMasterById(@PathVariable Integer id) {
        masterService.deleteMasterById(id);
    }
}
