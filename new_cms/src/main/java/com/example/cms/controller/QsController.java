package com.example.cms.controller;

import com.example.cms.model.entity.Qs_rankings;
import com.example.cms.model.entity.StudentUser;
import com.example.cms.model.repository.QsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class QsController {

    @Autowired
    private final QsRepository repository;

    public QsController(QsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/qsrankings")
    List<Qs_rankings> retrieveAllQsRankings() {
        return repository.findAll();
    }

    @GetMapping("/qsrankings/upperbound/{UB}")
    List<Qs_rankings> retrieveQsUpper(@PathVariable("UB") Long UB){
        return repository.findTillUpperBound(UB);
    }

    @GetMapping("/qsrankings/lowerbound/{LB}")
    List<Qs_rankings> retrieveQsLower(@PathVariable("LB") Long LB){
        return repository.findTillLowerBound(LB);
    }

    @GetMapping("/qsrankings/bound/{lb}/{ub}")
    List<Qs_rankings> retrieveQsBounds(@PathVariable("lb") Long lb, @PathVariable("ub") Long ub){
        return repository.findSpecificRankings(lb, ub);
    }

    @GetMapping("/qsrankings/searchname/{Qs_rankingName}")
    List<Qs_rankings> retrieveQsRankingName(@PathVariable("Qs_rankingName") String Qs_rankingName){
        return repository.searchQsName(Qs_rankingName);
    }


}
