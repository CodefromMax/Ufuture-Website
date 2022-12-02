package com.example.cms.controller.exceptions;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String id) {
        super("Could not find student " + id);
    }
}
