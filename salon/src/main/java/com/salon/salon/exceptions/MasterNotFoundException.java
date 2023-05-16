package com.salon.salon.exceptions;

public class MasterNotFoundException extends RuntimeException {
    public MasterNotFoundException(Integer id) {
        super("Couldn't find master with ID: " + id); 
    }

    public MasterNotFoundException(String name) {
        super("Couldn't find master with NAME: " + name); 
    }
    
}
