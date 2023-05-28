package com.salon.salon.services;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salon.salon.models.Client;
import com.salon.salon.models.Master;
import com.salon.salon.models.Order;
import com.salon.salon.models.Servise;
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

    public List<Order> getByMaster(Master master) {
        return orderRepository.findByMaster(master);
    }

    public List<Order> getOrderByDay(Date day) {
        return orderRepository.findByDay(day);
    }

    public List<Order> getOrderByServise(Servise service) {
        Set<Servise> servises = new HashSet<>();
        servises.add(service);
        return orderRepository.findByServicesIn(servises);
    }

    public List<Order> getOrdersByServiseList(List<Servise> servisesList) {
        Set<Servise> servisesSet = servisesList.stream().collect(Collectors.toSet());
        return orderRepository.findByServicesIn(servisesSet);
    }

}
