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

/**
 * Класс, представляющий модель клиента, который имеет отображение в таблице clients бд 
 */
@Entity
@Table(name="clients")
@Data
public class Client {
    /**
     * Первичный ключ таблицы, гененрируется в бд
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Столбец имени клиента
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Столбец фамилии клиента
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Столбец номер телефона клиента
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Столбец даты рождения клиента
     */
    @Column(name = "birthdate")
    private Date birthDate;

    /**
     * связь с таблицей заказа (внешний ключ)
     */
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    /**
     * Метод редактирования клиента
     * @param client принимаемый параметр, с которым будет сравниваться редактируемый объект
     */
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
        if(client.orders != null) {
            this.orders = client.orders;
        }
    }

}
