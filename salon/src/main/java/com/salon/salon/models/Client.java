package com.salon.salon.models;

import java.sql.Date;
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
@Table(name="clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birthdate")
    private Date birthDate;

    // связь с таблицей заказа (там колонка - внешний ключ)
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    public void updateClient(Client client) {
        if(client.firstName != null) {
            this.firstName = client.firstName;
        }
        if(client.lastName != null) {
            this.lastName = client.lastName;
        }
        if(client.birthDate != null) {
            this.birthDate = client.birthDate;
        }
        if(client.phoneNumber != null) {
            this.phoneNumber = client.phoneNumber;
        }
    }

}
