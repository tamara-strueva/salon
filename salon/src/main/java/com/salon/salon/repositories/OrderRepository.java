package com.salon.salon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salon.salon.models.Order;
import com.salon.salon.models.Client;
import com.salon.salon.models.Master;

import java.util.List;
import java.sql.Date;
import com.salon.salon.models.Servise;
import java.util.Set;

/**
 * Интерфейс репозитория для работы с таблицей клиента в бд
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    /**
     * Метод поиска заказа в бд по клиенту
     * @param client
     * @return список заказов удовлетворяющих условию поиска
     */
    List<Order> findByClient(Client client);

    /**
     * Метод поиска заказа в бд по мастеру
     * @param master
     * @return список заказов удовлетворяющих условию поиска
     */
    List<Order> findByMaster(Master master);

    /**
     * Метод поиска заказа в бд по дню записи
     * @param day
     * @return список заказов удовлетворяющих условию поиска
     */
    List<Order> findByDay(Date day);

    /**
     * Метод поиска заказа в бд по услугам
     * @param servises
     * @return список заказов удовлетворяющих условию поиска
     */
    List<Order> findByServicesIn(Set<Servise> servises);
}
