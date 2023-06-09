package com.salon.salon.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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
import com.salon.salon.models.Master;
import com.salon.salon.models.Order;
import com.salon.salon.models.Servise;
import com.salon.salon.services.ClientService;
import com.salon.salon.services.MasterService;
import com.salon.salon.services.OrderService;
import com.salon.salon.services.ServiseService;

@RestController
@RequestMapping("/orders")
public class OrderContoller {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ServiseService serviseService;
    @Autowired
    private MasterService masterService;

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

    @GetMapping("/getcn/{name}")
    public List<Order> getOrderByClientName(@PathVariable String name) {
        try {
            List<Client> clients = clientService.getClientsByFirstName(name);
            List<Order> orders = new ArrayList<>();
            for(Client client: clients) {
                List<Order> orderToAdd = orderService.getOrderByClient(client);
                orders.addAll(orderToAdd);
            }
            return orders;
        } catch (NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Some data doesn't exist...");
        }
    }
    
    @GetMapping("/getcl/{lastName}")
    public List<Order> getOrderByClientLastName(@PathVariable String lastName) {
        try {
            List<Client> clients = clientService.getClientsByLastName(lastName);
            List<Order> orders = new ArrayList<>();
            for(Client client: clients) {
                List<Order> orderToAdd = orderService.getOrderByClient(client);
                orders.addAll(orderToAdd);
            }
            return orders;
        } catch (NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Some data doesn't exist...");
        }
    }

    // поиск заказов по мастеру
    @GetMapping("/getms/{speciality}")
    public List<Order> getOrderByMasterSpeciality(@PathVariable String speciality) {
        try {
            List<Master> masters = masterService.getMastersBySpeciality(speciality);
            List<Order> orders = new ArrayList<>();
            for(Master master: masters) {
                List<Order> orderToAdd = orderService.getByMaster(master);
                orders.addAll(orderToAdd);
            }
            return orders;
        } catch (NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Some data doesn't exist...");
        }
    }

    @GetMapping("/getmn/{name}")
    public List<Order> getOrderByMasterName(@PathVariable String name) {
        try {
            List<Master> masters = masterService.getMastersByName(name);
            List<Order> orders = new ArrayList<>();
            for(Master master: masters) {
                List<Order> orderToAdd = orderService.getByMaster(master);
                orders.addAll(orderToAdd);
            }
            return orders;
        } catch (NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Some data doesn't exist...");
        }
    }

    // by date
    @GetMapping("/getd/{day}")
    public List<Order> getOrderByDate(@PathVariable Date day) {
        return orderService.getOrderByDay(day);
    }

    @GetMapping("/getsn/{serviseName}")
    public List<Order> getOrdersByServiseName(@PathVariable String serviseName) {
        List<Servise> servises = serviseService.getServisesByName(serviseName);
        return orderService.getOrdersByServiseList(servises); 
    }

    @PostMapping("/add")
    public void saveOrder(@RequestBody Order order) {      
        try{
            Set<Servise> services = order.getServices();
            if(!services.isEmpty()) {
                order.setServices(services);
                for(Servise servise: services) {
                    servise.getOrders().add(order);
                }
                orderService.saveOrder(order);
            } else {
                orderService.saveOrder(order);
            }
        } catch (NoSuchElementException exception) {
            
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrderById(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
    }

    // изменение
    @PutMapping("/edit/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Integer id) {
        try{
            Order baseOrder = orderService.getOrderById(id);
            baseOrder.updateOrder(order);
            orderService.saveOrder(baseOrder);
            return ResponseEntity.ok(baseOrder);
        } catch(NoSuchElementException exception) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Order with ID: " + id + " doesn't exist...");
        }
    }
    
}
