package com.salon.salon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salon.salon.models.Client;
import com.salon.salon.models.Order;
import com.salon.salon.repositories.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getOrderByClient(Client client) {
        return orderRepository.findByClient(client);
    }
    
}
