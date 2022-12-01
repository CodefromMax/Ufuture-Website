package com.example.cms.controller;
import com.example.cms.model.entity.Events;
import com.example.cms.model.repository.EventsRepository;
import com.example.cms.model.repository.StudentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController


public class EventsController {

    @Autowired
    private EventsRepository repository;

    @Autowired
    private StudentUserRepository studentRepository;


    public EventsController(EventsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/events")
    List<Events> showEvents() {return repository.showAllEvents();}

}

