package com.salon.salon.services;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salon.salon.models.Master;
import com.salon.salon.models.Schedule;
import com.salon.salon.repositories.ScheduleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void deleteScheduleById(Integer id) {
        scheduleRepository.deleteById(id);
    }
    
    public Schedule getScheduleById(Integer id) {
        return scheduleRepository.findById(id).get();
    }

    public List<Schedule> getScheduleByMaster(Master master) {
        return scheduleRepository.findByMaster(master);
    }

    public List<Schedule> getScheduleByTimeBegin(Time time) {
        return scheduleRepository.findByTimeBegin(time);
    }

}
