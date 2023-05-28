package com.salon.salon.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Master;
import com.salon.salon.models.Schedule;
import java.sql.Time;

/**
 * Интерфейс репозитория для работы с таблицей графиков работы в бд
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    /**
     * Метод поиска графика в бд по мастеру
     * @param master
     * @return список клиентов удовлетворяющих условию
     */
    List<Schedule> findByMaster(Master master);

    /**
     * Метод поиска графика в бд по дню 
     * @param day
     * @return список клиентов удовлетворяющих условию
     */
    List<Schedule> findByDay(Date day);

    /**
     * Метод поиска графика в бд по времени начала работы 
     * @param timeBegin
     * @return список клиентов удовлетворяющих условию
     */
    List<Schedule> findByTimeBegin(Time timeBegin);
    
}
