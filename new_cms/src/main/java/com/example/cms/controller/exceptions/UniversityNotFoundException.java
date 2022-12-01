package com.example.cms.controller.exceptions;

public class UniversityNotFoundException extends RuntimeException{
    public UniversityNotFoundException(String id) {
        super("Could not find university " + id);
    }
}
