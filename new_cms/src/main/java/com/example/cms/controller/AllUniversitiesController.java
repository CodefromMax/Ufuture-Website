package com.example.cms.controller;

import com.example.cms.model.entity.All_universities;
import com.example.cms.model.repository.All_universitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.cms.controller.exceptions.StudentNotFoundException;
import com.example.cms.model.entity.StudentUser;
import com.example.cms.model.repository.StudentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class AllUniversitiesController {

    @Autowired

    private final All_universitiesRepository repository;

    @Autowired
    public AllUniversitiesController(All_universitiesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/universities/{uniname}")
    List<All_universities> searchUniversity(@PathVariable("uniname") String uniname){
        return repository.searchUni(uniname);
    }

}
