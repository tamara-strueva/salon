package com.salon.salon.models;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "schedules")
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "master_schedule_id")
    private Master master_gr;

    @Column(name = "day")
    private Date day;

    @Column(name="time_begin")
    private Time timeBegin;

    @Column(name="time_end")
    private Time timeEnd;
    
    public void updateSchedule(Schedule schedule) {
        if(schedule.master_gr != null) {
            this.master_gr = schedule.master_gr;
        }
        if(schedule.day != null) {
            this.day = schedule.day;
        }
        if(schedule.timeBegin != null) {
            this.timeBegin = schedule.timeBegin;
        }
        if(schedule.timeEnd != null) {
            this.timeEnd = schedule.timeEnd;
        }
    }
}
