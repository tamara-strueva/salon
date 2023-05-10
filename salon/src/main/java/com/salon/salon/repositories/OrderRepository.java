package com.salon.salon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Order;
import com.salon.salon.models.Client;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByClient(Client client);
    
}
