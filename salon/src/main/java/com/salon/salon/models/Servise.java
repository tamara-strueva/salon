package com.salon.salon.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Entity
@Table(name="services")
@Data
// @NoArgsConstructor
// @AllArgsConstructor
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
    private Integer basePrice;

    // связь с таблицей заказа (там колонка - внешний ключ)
    // @JsonIgnore
    // @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Order.class)
    // private Set<Order> orders = new HashSet<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "services", cascade = CascadeType.ALL)
    Set<Order> orders = new HashSet<Order>(0);

    // public void setId(Integer id) {this.id = id;}

    // public int getId() {return this.id;}

    // public void setName(String name) {this.name = name;}

    // public String getName() {return this.name;}

    // public void setDescription(String description) {this.description = description;}

    // public String getDescription() {return this.description;}

    // public void setDuration(Integer duration) {this.duration = duration;}

    // public Integer getDuration() {return this.duration;}

    // public void setBasePrice(Integer basePrice) {this.basePrice = basePrice;}

    // public int getPasePrice() {return this.basePrice;}

    // public void setOrders(Set<Order> orders) {this.orders = orders;}

    // public Set<Order> getOrders() {return this.orders;}

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
    
    // public void addNewOrder(Order newOrder) {
    //     this.orders.add(newOrder);
    // }
}
