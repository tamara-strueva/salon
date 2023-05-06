package com.salon.salon.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByFirstName(String firstName);
    List<Client> findByLastName(String lastName);
    List<Client> findByFirstNameAndLastName(String firstName, String lastName);
    List<Client> findByBirthDate(Date birthDate); // поиск клиентов по дате рождения
    
}
