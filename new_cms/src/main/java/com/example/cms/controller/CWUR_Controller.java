package com.example.cms.controller;

import com.example.cms.model.entity.CWUR_rankings;
import com.example.cms.model.repository.CWURRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CWUR_Controller {

    @Autowired
    public final CWURRepository repository;

    public CWUR_Controller(CWURRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cwurrankings")
    List<CWUR_rankings> findAllCWUR() {return repository.findAll();}

    @GetMapping("/cwurrankings/{cwurName}")
    List<CWUR_rankings> findCWURByName(@PathVariable("cwurName") String cwurName) {return repository.searchCwurName(cwurName);}
}
