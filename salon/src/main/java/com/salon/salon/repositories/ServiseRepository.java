package com.salon.salon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Servise;

/**
 * Интерфейс репозитория для работы с таблицей услуг в бд
 */
public interface ServiseRepository extends JpaRepository<Servise, Integer> {
    /**
     * Метод поиска клиента в бд по частичному названию услуги
     * @param name
     * @return список услуг удовлетворяющих условию поиска
     */
    List<Servise> findByNameContaining(String name);

    /**
     * Метод поиска услуги по ее полному названию 
     * @param name
     * @return еднственную услугу, удовлетворяющую условию поиска
     */
    Servise findByNameContains(String name);

}
