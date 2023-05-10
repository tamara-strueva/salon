package com.salon.salon.controllers;

import java.sql.Date;
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

import com.salon.salon.exceptions.ClientNotFoundExсeption;
import com.salon.salon.exceptions.CustomNotFoundExсeption;
import com.salon.salon.models.Client;
import com.salon.salon.services.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientContoller {
    @Autowired
    public ClientService clientService;

    @GetMapping("")
    public String show() {
        return "index";
    }

    @PostMapping("/add")
    public void saveClient(@RequestBody Client client) {
        clientService.saveClient(client);
    }

    @GetMapping("/get")
    public List<Client> getAllClientsList() {
        return clientService.getAllClients();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        try{
            Client client = clientService.getClientById(id);
            return ResponseEntity.ok(client);
        } catch (NoSuchElementException exeption) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Client with ID: " + id + " doesn't exist...");
        }
        
    }

    @GetMapping("/getn/{name}")
    public ResponseEntity<?> getClientsByFirstName(@PathVariable String name) {
        try{
            List<Client> clients = clientService.getClientsByFirstName(name);
            log.info("FIRSTNME {}", name);
            return ResponseEntity.ok(clients);
        } catch (ClientNotFoundExсeption exсeption) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Client with NAME: " + name + " doesn't exist...");
        }
    }

    @GetMapping("/gets/{name}")
    public ResponseEntity<?> getClientsByLastName(@PathVariable String name) {
        try{
            List<Client> clients = clientService.getClientsByLastName(name);
            return ResponseEntity.ok(clients);
        } catch (ClientNotFoundExсeption exсeption) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Client with SURNAME: " + name + " doesn't exist...");
        }
    }

    @GetMapping("/getb/{date}")
    public ResponseEntity<?> getClientsByBirthDate(@PathVariable Date date) {
        try{
            List<Client> clients = clientService.getClientByBirthDate(date);
            return ResponseEntity.ok(clients);
        } catch (ClientNotFoundExсeption exсeption) {
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Client with BIRTHDATE: " + date + " doesn't exist...");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Integer id) {
        clientService.deleteClientById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateClient(@RequestBody Client client, @PathVariable Integer id) {
        try {
            Client baseClient = clientService.getClientById(id);
            baseClient.updateClient(client);
            clientService.saveClient(baseClient);
            return ResponseEntity.ok(baseClient);
        } catch(ClientNotFoundExсeption exception){
            throw new CustomNotFoundExсeption(HttpStatus.NOT_FOUND, "Client with ID: " + id + " doesn't exist...");
        } 
    }
}
