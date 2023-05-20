package com.salon.salon.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "masters")
@Data
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "work_experience")
    private Integer workExperience;

    @Column(name = "rate")
    private Float rate = 1F;

    @JsonIgnore
    @OneToMany(mappedBy = "master")
    private List<Schedule> schedules;

    // связь с таблицей заказа (там колонка - внешний ключ)
    @JsonIgnore
    @OneToMany(mappedBy = "master")
    private List<Order> orders;

    public void updateMaster(Master master) {
        if(master.firstName != null) {
            this.firstName = master.firstName;
        }
        if(master.lastName != null) {
            this.lastName = master.lastName;
        }
        if(master.speciality != null) {
            this.speciality = master.speciality;
        }
        if(master.workExperience != null) {
            this.workExperience = master.workExperience;
        }
        if(master.rate != null) {
            this.rate = master.rate;
        }
        if(master.schedules != null) {
            this.schedules = master.schedules;
        }
        if(master.orders != null) {
            this.orders = master.orders;
        }
    }

}
