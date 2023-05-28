package com.salon.salon.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Client;

/**
 * Интерфейс репозитория для работы с таблицей клиента в бд
 */
public interface ClientRepository extends JpaRepository<Client, Integer> {
    /**
     * Метод поиска клиента в бд по имени
     * @param firstName принимаемый параметр для поиска
     * @return список клиентов удовлетворяющих имени
     */
    List<Client> findByFirstName(String firstName);

    /**
     * Метод поиска клиента в бд по фамилии
     * @param lastName принимаемый параметр для поиска
     * @return список клиентов удовлетворяющих фамилии
     */
    List<Client> findByLastName(String lastName);
    
    /**
     * Метод поиска клиента в бд по имени и фамилии
     * @param firstName принимаемый параметр имени для поиска
     * @param lastName принимаемый параметр фамилии для поиска
     * @return список клиентов удовлетворяющих имени и фамилии
     */
    List<Client> findByFirstNameAndLastName(String firstName, String lastName);
    
    /**
     * Метод поиска клиента по дате рождения
     * @param birthDate принимаетый параметр для поиска
     * @return список клиентов удовлетворяющих дате рождения
     */
    List<Client> findByBirthDate(Date birthDate); // поиск клиентов по дате рождения
    
}
