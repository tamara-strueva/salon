package com.salon.salon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salon.salon.models.Order;
import com.salon.salon.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderContoller {
    @Autowired
    private OrderService orderService;

    @GetMapping("/get")
    public List<Order> getAllOrdersList() {
        return orderService.getAllOrders();
    }

    @PostMapping("/add")
    public void saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrderById(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
    }
    
}
