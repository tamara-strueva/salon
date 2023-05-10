package com.salon.salon.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salon.salon.exceptions.CustomNotFoundExсeption;
import com.salon.salon.models.Client;
import com.salon.salon.models.Order;
import com.salon.salon.models.Servise;
import com.salon.salon.services.ClientService;
import com.salon.salon.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderContoller {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/get")
    public List<Order> getAllOrdersList() {
        return orderService.getAllOrders();
    }

    @GetMapping("/get/{id}")
    public Order getAllOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/getc/{id}")
    public List<Order> getOrderByClient(@PathVariable Integer id) {
        Client client = clientService.getClientById(id);
        return orderService.getOrderByClient(client);
    }

    // поиск заказов по мастеру

    @PostMapping("/add")
    public void saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrderById(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
    }

    // изменение
    @PutMapping("/edit/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Integer id) {
        try{
            // новая услуга должна сохраниться в результирующую таблицу manytomany
            Order baseOrder = orderService.getOrderById(id);
            baseOrder.updateOrder(order);
            // orderService.saveOrder(baseOrder);
            List<Servise> servisesToAdd = order.getServices();
            for(Servise servise: servisesToAdd) {
                orderService.insertRelationTable(baseOrder, servise);
            }
            orderService.saveOrder(baseOrder);
            return ResponseEntity.ok(baseOrder);
        } catch(NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Order with ID: " + id + " doesn't exist...");
        }
    }
    
}
