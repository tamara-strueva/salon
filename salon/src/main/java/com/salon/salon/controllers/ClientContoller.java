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

import com.salon.salon.models.Client;
import com.salon.salon.services.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientContoller {
    @Autowired
    private ClientService clientService;

    @GetMapping("")
    public String show() {
        return "index";
    }

    @PostMapping("/add")
    public void saveClient(@RequestBody Client client) {
        clientService.saveClient(client);
        log.info("INSERT {}, {}, {}", client.getFirstName(), client.getLastName(), client.getBirthDate());
    }

    @GetMapping("/get")
    public List<Client> getAllClientsList() {
        return clientService.getAllClients();
    }

    @GetMapping("/get/{id}")
    public Client getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/getn/{name}")
    public List<Client> getClientsByFirstName(@PathVariable String name) {
        return clientService.getClientsByFirstName(name);
    }

    @GetMapping("/gets/{name}")
    public List<Client> getClientsByLastName(@PathVariable String name) {
        return clientService.getClientsByLastName(name);
    }

    @GetMapping("/getb/{date}")
    public List<Client> getClientsByBirthDate(@PathVariable Date date) {
        return clientService.getClientByBirthDate(date);
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
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
    }
}
