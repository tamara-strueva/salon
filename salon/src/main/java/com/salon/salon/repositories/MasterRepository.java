package com.salon.salon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Master;

public interface MasterRepository extends JpaRepository<Master, Integer> {
    List<Master> findByFirstName(String firstName);
    List<Master> findBySpeciality(String speciality);
    // List<Master> findBySchedules(List<Schedule> schedules);
}
