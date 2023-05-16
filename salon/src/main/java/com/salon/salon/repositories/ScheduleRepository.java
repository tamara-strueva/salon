package com.salon.salon.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Master;
import com.salon.salon.models.Schedule;
import java.sql.Time;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByMaster(Master master);
    List<Schedule> findByDay(Date day);
    List<Schedule> findByTimeBegin(Time timeBegin);
    
}
