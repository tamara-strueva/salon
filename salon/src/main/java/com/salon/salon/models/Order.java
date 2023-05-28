package com.salon.salon.models;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

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
import lombok.Getter;
import lombok.Setter;
 
/**
 * Класс, представляющий модель заказа, который имеет отображение в таблице orders бд 
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    /**
     * Первичный ключ таблицы, гененрируется в бд
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Столбец связи заказа с мастером 
     */
    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    /**
     * Столбец связи с клиентом
     */
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * Связь заказа с услугами
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_service",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id"))
    Set<Servise> services = new HashSet<Servise>(0);

    /**
     * Столбец даты записи
     */
    @Column(name = "day")
    private Date day;

    /**
     * Столбец времени начала записи
     */
    @Column(name = "time_begin")
    private Time timeBegin;
    
    /**
     * Столбец времени окончания записи и оказания услуг
     */
    @Column(name = "time_end")
    private Time timeEnd;

    /**
     * Метод редактирования заказа
     * @param order принимаемый параметр, с которым будет сравниваться редактируемый объект
     */
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
