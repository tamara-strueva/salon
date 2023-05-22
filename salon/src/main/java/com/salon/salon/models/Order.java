package com.salon.salon.models;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
// @NoArgsConstructor
// @AllArgsConstructor
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

    // @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // @JoinTable(name = "order_service",
    //     joinColumns = {@JoinColumn(name = "order_id")},
    //     inverseJoinColumns = {@JoinColumn(name = "service_id")})
    // private Set<Servise> services = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_service",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id"))
    Set<Servise> services = new HashSet<Servise>(0);

    @Column(name = "day")
    private Date day;

    @Column(name = "time_begin")
    private Time timeBegin;
    
    @Column(name = "time_end")
    private Time timeEnd;

    // public Set<Servise> getAllServises() {return this.services;}

    // public void setNewServises(Set<Servise> services) {this.services = services;}

    // public void setId(Integer id) {this.id = id;}

    // public int getId() {return this.id;}

    // public void setMaster(Master master) {this.master = master;}

    // public Master getMaster() {return this.master;}

    // public void setClient(Client client) {this.client = client;}

    // public Client getClient() {return this.client;}

    // public void setServices(Set<Servise> servises) {this.services = servises;}

    // public Set<Servise> getServises() {return this.services;}

    // public void setDay(Date day) {this.day = day;}

    // public Date getDay() {return this.day;}

    // public void setTimeBegin(Time timeBegin) {this.timeBegin = timeBegin;}

    // public Time getTimeBegin() {return this.timeBegin;}

    // public void setTimeEnd(Time timeEnd) {this.timeEnd = timeEnd;}

    // public Time getTimeEnd() {return this.timeEnd;}

    public void updateOrder(Order order) {
        if(order.master != null) {
            this.master = order.master;
        }
        if(order.client != null) {
            this.client = order.client;
        }
        if(order.services != null){
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