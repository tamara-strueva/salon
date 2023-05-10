package com.salon.salon.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Integer id) {
        super("Couldn't find order with ID: " + id);
    }
    
}
