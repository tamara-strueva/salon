package com.salon.salon.exceptions;

import java.sql.Date;

public class ClientNotFoundExсeption extends RuntimeException{
    public ClientNotFoundExсeption(Integer id){
        super("Couldn't find client with id: " + id);
    }

    public ClientNotFoundExсeption(String firstName) {
        super("Couldn't find client with name: " + firstName);
    }

    public ClientNotFoundExсeption(Date birthDate) {
        super("Couldn't find client with birthdate: " + birthDate);
    }
    
}
