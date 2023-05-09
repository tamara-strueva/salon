package com.salon.salon.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="services")
@Data
public class Servise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "base_price")
    private Float basePrice;

    // связь с таблицей заказа (там колонка - внешний ключ)
    @ManyToMany(mappedBy = "servises", targetEntity = Order.class)
    private List<Order> orders;

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
