package com.example.cms.controller;

import com.example.cms.model.entity.*;
import com.example.cms.model.repository.All_universitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import com.example.cms.controller.exceptions.StudentNotFoundException;
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

    /**
     * 0 Qs_ranking 1 Cwur_ranking 2 Times_rankings
     * @param qsId
     * @return
     */
    @GetMapping("/universitiesByType/{type}/{qsId}")
    List<All_universities> queryByType(@PathVariable("qsId") String qsId,
                                       @PathVariable("type") Integer type){


        All_universities universities = new All_universities();
        if ("0".equals(type + "")) {
            Qs_rankings rankings = new Qs_rankings();
            rankings.setQS_ranking_id(qsId);
            universities.setQs_rankings(rankings);
        }

        if ("1".equals(type + "")) {
            CWUR_rankings rankings = new CWUR_rankings();
            rankings.setCwur_Id(qsId);
            universities.setCwur_rankings(rankings);
        }


        if ("2".equals(type + "")) {
            Times_rankings rankings = new Times_rankings();
            rankings.setTimes_Id(qsId);
            universities.setTimes_rankings(rankings);
        }


        Example<All_universities> wrapper = Example.of(universities);
        return repository.findAll(wrapper);
    }

    @GetMapping("/universities/showall")
    List<All_universities> showAllUni (){return repository.findAll();}

}
