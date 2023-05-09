package com.salon.salon.models;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "order_servise",
        joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "servise_id", referencedColumnName = "id"))
    private Set<Servise> servises;

    @Column(name = "day")
    private Date day;

    @Column(name = "time_begin")
    private Time timeBegin;
    
    @Column(name = "time_end")
    private Time timeEnd;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Column(name = "date_of_edit")
    private Date dateOfEdit;

    public void updateOrder(Order order) {
        if(order.master != null) {
            this.master = order.master;
        }
        if(order.client != null) {
            this.client = order.client;
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
}
