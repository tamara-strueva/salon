package com.salon.salon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.salon.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
