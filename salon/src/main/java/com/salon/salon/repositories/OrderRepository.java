package com.salon.salon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salon.salon.models.Order;
import com.salon.salon.models.Client;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByClient(Client client);
    
    // @Modifying 
    @Query(value = "insert into test.servise_order so (so.order_id, so.service_id) VALUES(:order_id, :service_id)", nativeQuery = true) void insertRelationTable (@Param("order_id") int orderId, @Param("service_id") int serviceId);
}
