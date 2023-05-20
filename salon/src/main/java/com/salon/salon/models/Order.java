package com.salon.salon.models;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(mappedBy = "orders", targetEntity = Servise.class, cascade=CascadeType.MERGE)
    private Set<Servise> services;

    @Column(name = "day")
    private Date day;

    @Column(name = "time_begin")
    private Time timeBegin;
    
    @Column(name = "time_end")
    private Time timeEnd;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    // delete
    @Column(name = "date_of_edit")
    private Date dateOfEdit;

    public void updateOrder(Order order) {
        if(order.master != null) {
            this.master = order.master;
        }
        if(order.client != null) {
            this.client = order.client;
        }
        if(order.services != null){
            // this.services.addAll(order.services);
            this.services = order.services;
        } 
        if(order.day != null) {
            this.day = order.day;
        }
        if(order.timeBegin != null) {
            this.timeBegin = order.timeBegin;
        }
        if(order.timeEnd != null) {
            this.timeEnd = order.timeEnd;
        }
    }

    public void setNewServises(List<Servise> newServises) {
        for(Servise servise: newServises) {
            this.services.add(servise);
        }
    }
}

// this.setNewServises(order.getServices());
// for(Servise servise: order.getServices()) {
//     servise.addNewOrder(this);
// }
// List<Servise> newList = Stream.concat(this.services.stream(), order.services.stream()).collect(Collectors.toList());
// this.services = newList;

// for(int i = 0; i < order.services.size(); i++) {
//     this.services.add(order.services.get(i));
// }
// for(Servise servise: order.services) {
//     this.services.add(servise);
// }