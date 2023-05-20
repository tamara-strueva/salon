package com.salon.salon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salon.salon.models.Order;
import com.salon.salon.models.Client;
import com.salon.salon.models.Master;

import java.util.List;
import java.sql.Date;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByClient(Client client);
    List<Order> findByMaster(Master master);
    List<Order> findByDay(Date day);
    
    // @Modifying 
    // @Query(value = "insert into servise_order (order_id, service_id) VALUES(:order_id, :service_id)", nativeQuery = true) 
    // public void insertRelationTable(@Param("order_id") int orderId, @Param("service_id") int serviceId);

}
