package com.salon.salon.controllers;

import java.sql.Time;
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

import com.salon.salon.models.Master;
import com.salon.salon.models.Schedule;
import com.salon.salon.services.MasterService;
import com.salon.salon.services.ScheduleService;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private MasterService masterService;

    @PostMapping("/add")
    public void saveSchedule(@RequestBody Schedule schedule) {
        scheduleService.saveSchedule(schedule);
    }

    @GetMapping("/get")
    public List<Schedule> getAllSchedulesList() {
        return scheduleService.getAllSchedules();
    }

    // get by id

    @GetMapping("/getm/{name}/{lastName}")
    public List<Schedule> getScheduleByMaster(@PathVariable String name, @PathVariable String lastName) {
        Master master = masterService.getMasterByFirstAndLastName(name, lastName).get(0);
        return scheduleService.getScheduleByMaster(master);
    }

    @GetMapping("/gett/{time}")
    public List<Schedule> getScheduleByTimeBegin(@PathVariable Time time) {
        return scheduleService.getScheduleByTimeBegin(time);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Integer id) {
        scheduleService.deleteScheduleById(id);
    }

    @PutMapping("/edit/{id}") // ??
    public ResponseEntity<?> updateClient(@RequestBody Schedule schedule, @PathVariable Integer id) {
        try {
            Schedule baseSchedule = scheduleService.getScheduleById(id);
            baseSchedule.updateSchedule(schedule);
            scheduleService.saveSchedule(baseSchedule);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
