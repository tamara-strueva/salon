package com.salon.salon.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salon.salon.models.Client;
import com.salon.salon.repositories.ClientRepository;

import jakarta.transaction.Transactional;

/**
 * Класс сервиса клентов
 */
@Service
@Transactional
public class ClientService {
    /**
     * Создание репозитория с которым будет взаимодействовать сервис
     */
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Метод вывода всех клиентов в бд
     * @return список всех клиентов
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Метод сохранения склиента в бд
     * @param client
     */
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    /**
     * Метод удаления клтента по уникальному идентификатору
     * @param id
     */
    public void deleteClientById(Integer id) {
        clientRepository.deleteById(id);
    }

    /**
     * Метод получения единственного клиента по уникальному идентификатору
     * @param id
     * @return единственного клиента по его id
     */
    public Client getClientById(Integer id) {
        return clientRepository.findById(id).get();
    }

    /**
     * Метод поиска клиента по дате рождения
     * @param birthDate
     * @return список клиентов, удовлетворяющих условию поиска
     */
    public List<Client> getClientByBirthDate(Date birthDate) {
        return clientRepository.findByBirthDate(birthDate);
    }

    /**
     * Метод поиска клиента по имени
     * @param firstName
     * @return список клиентов, удовлетоворяющих условию
     */
    public List<Client> getClientsByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

    /**
     * Метод поиска клиентов по фамилии
     * @param lastName
     * @return список клиентов, удовлетворяющих условию поиска
     */
    public List<Client> getClientsByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    /**
     * Метод поиска клиентов по имени и фамилии
     * @param firstName
     * @param lastName
     * @return список клиентов, удовлетворяющих условию поиска
     */
    public List<Client> getClientsByFirstAndLastName(String firstName, String lastName) {
        return clientRepository.findByFirstNameAndLastName(firstName, lastName);
    }
        
}
