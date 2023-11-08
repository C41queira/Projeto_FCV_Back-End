package com.projetofcv.rosangelaestetica.service.exception;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(Object obj){
        super("Resource not found. Id: " + obj);
    }
}
