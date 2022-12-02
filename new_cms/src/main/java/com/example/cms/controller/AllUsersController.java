package com.example.cms.controller;

import com.example.cms.model.entity.*;
import com.example.cms.model.repository.AllUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class AllUsersController {
    @Autowired
    private final AllUsersRepository repository;

    public AllUsersController(AllUsersRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/AllUsers")
    List<AllUsers> retrieveAllUsers() {
        return repository.findAll();
    }
}
