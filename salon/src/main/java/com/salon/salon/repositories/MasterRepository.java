package com.salon.salon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Master;

/**
 * Интерфейс репозитория для работы с таблицей мастеров в бд
 */
public interface MasterRepository extends JpaRepository<Master, Integer> {
    /**
     * Метод поиска мастеров по имени
     * @param firstName принимаемый параметр для поиска
     * @return список клиентов удовлетворяющих имени
     */
    List<Master> findByFirstName(String firstName);

    /**
     * Метод поиска мастеров по фамилии
     * @param lastName
     * @return список клиентов удовлетворяющих фамилии
     */
    List<Master> findByLastName(String lastName);

    /**
     * Метод поиска мастеров по имени и фамилии
     * @param firstName
     * @param lastName
     * @return список клиентов удовлетворяющих имени и фамилии
     */
    List<Master> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Метод поиска мастеров по специальности
     * @param speciality
     * @return список клиентов удовлетворяющих спецтальности
     */
    List<Master> findBySpecialityContaining(String speciality);

    /**
     * Метод поиска мастеров по имени и специальности
     * @param firstName
     * @param speciality
     * @return список клиентов удовлетворяющих имени и специальности
     */
    List<Master> findByFirstNameAndSpeciality(String firstName, String speciality);
}
