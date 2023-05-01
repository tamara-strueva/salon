package com.salon.salon.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
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
    private Float rate;

    @OneToMany(mappedBy = "master_gr")
    private List<Schedule> schedules;

    // связь с таблицей заказа (там колонка - внешний ключ)
    @OneToMany(mappedBy = "master")
    private List<Order> orders;

}
