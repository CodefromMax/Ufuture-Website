package com.example.cms.controller;

import com.example.cms.model.entity.CWUR_rankings;
import com.example.cms.model.entity.Times_rankings;
import com.example.cms.model.repository.CWURRepository;
import com.example.cms.model.repository.TimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class TimesController {
    @Autowired
    public final TimesRepository repository;

    public TimesController(TimesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/timesrankings")
    List<Times_rankings> findAlltimes() {return repository.findAll();}
}
