package com.salon.salon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salon.salon.models.Client;
import com.salon.salon.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClientById(Integer id) {
        clientRepository.deleteById(id);
    }

    public Client getClientById(Integer id) {
        return clientRepository.findById(id).get();
    }
    
}
