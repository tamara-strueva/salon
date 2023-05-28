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

/**
 * Класс, представляющий модель мастера, который имеет отображение в таблице masters бд 
 */
@Entity
@Table(name = "masters")
@Data
public class Master {
    /**
     * Первичный ключ таблицы, гененрируется в бд
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Столбец имени мастера
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Столбец фамилии мастера
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Столбец специальности мастера
     */
    @Column(name = "speciality")
    private String speciality;

    /**
     * Связь с таблицей графика работы (внешний ключ)
     */
    @JsonIgnore
    @OneToMany(mappedBy = "master")
    private List<Schedule> schedules;

    /**
     * Связь с таблицей заказов (внешний ключ)
     */
    @JsonIgnore
    @OneToMany(mappedBy = "master")
    private List<Order> orders;

    /**
     * Метод редактирования мвстера
     * @param master принимаемый параметр, с которым будет сравниваться редактируемый объект
     */
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
        if(master.schedules != null) {
            this.schedules = master.schedules;
        }
        if(master.orders != null) {
            this.orders = master.orders;
        }
    }

}
