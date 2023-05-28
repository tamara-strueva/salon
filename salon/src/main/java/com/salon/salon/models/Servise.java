package com.salon.salon.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Класс, представляющий модель услуги, который имеет отображение в таблице services бд 
 */
@Entity
@Table(name="services")
@Data
public class Servise {
    /**
     * Первичный ключ таблицы, гененрируется в бд
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Столбец названия услуги, который принимает только уникальные значения
     */
    @Column(name = "name", unique = true)
    private String name;

    /**
     * Столбец описания услуги
     */
    @Column(name = "description", columnDefinition = "text")
    private String description;

    /**
     * Столбец продолжительности оказания услуги
     */
    @Column(name = "duration")
    private Integer duration;

    /**
     * Столбец цены за оказание услуги
     */
    @Column(name = "base_price")
    private Integer basePrice;

    /**
     * Связь услуги с заказом
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "services", cascade = CascadeType.ALL)
    Set<Order> orders = new HashSet<Order>(0);

    /**
     * Метод редактирования услуги
     * @param servise принимаемый параметр, с которым будет сравниваться редактируемый объект
     */
    public void updateServise(Servise servise) {
        if(servise.name != null) {
            this.name = servise.name;
        }
        if(servise.description != null) {
            this.description = servise.description;
        }
        if(servise.duration != null) {
            this.duration = servise.duration;
        }
        if(servise.basePrice != null) {
            this.basePrice = servise.basePrice;
        }
    }
}
