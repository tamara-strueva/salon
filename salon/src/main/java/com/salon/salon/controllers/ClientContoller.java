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

import com.salon.salon.models.Client;
import com.salon.salon.services.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientContoller {
    @Autowired
    private ClientService clientService;

    @PostMapping("/add")
    public void saveClient(@RequestBody Client client) {
        clientService.saveClient(client);
        log.info("INSERT {}, {}, {}", client.getFirstName(), client.getLastName(), client.getBirthDate());
    }

    @GetMapping("/get")
    public List<Client> getAllClientsList() {
        return clientService.getAllClients();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Integer id) {
        clientService.deleteClientById(id);
    }

    @PutMapping("/edit/{id}") // ??
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
    

    // public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Integer id){
    //     try{
    //         Client baseClient = clientService.getClientById(id);
    //         baseClient.updateClient(client);
    //         clientService.saveClient(baseClient);
    //         return new ResponseEntity<>(HttpStatus.OK);

    //     } catch(NoSuchElementException e){
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
}
