package com.salon.salon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salon.salon.models.Servise;

public interface ServiseRepository extends JpaRepository<Servise, Integer> {
    List<Servise> findByNameContaining(String name);

    @Modifying 
    @Query(value = "insert into service_order (order_id, service_id) VALUES(:order_id, :service_id)", nativeQuery = true) 
    public void insertRelationTable(@Param("order_id") int orderId, @Param("service_id") int serviceId);
}
