package com.example.cms.controller.exceptions;

public class UniversityNotFoundException extends RuntimeException{
    public UniversityNotFoundException(String name) {
        super("Could not find university " + name);
    }
}
