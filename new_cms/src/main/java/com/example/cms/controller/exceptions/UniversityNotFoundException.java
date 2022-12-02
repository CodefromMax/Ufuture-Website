package com.example.cms.controller.exceptions;

public class UniversityNotFoundException extends RuntimeException{
    public UniversityNotFoundException(String Id) {
        super("Could not find university " + Id);
    }
}
