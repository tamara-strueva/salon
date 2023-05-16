package com.salon.salon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salon.salon.models.Order;
import com.salon.salon.models.Client;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByClient(Client client);
    
    // @Modifying 
    // @Query(value = "insert into servise_order (order_id, service_id) VALUES(:order_id, :service_id)", nativeQuery = true) 
    // public void insertRelationTable(@Param("order_id") int orderId, @Param("service_id") int serviceId);

}
