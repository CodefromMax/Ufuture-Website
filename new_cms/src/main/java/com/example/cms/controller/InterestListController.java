package com.example.cms.controller;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.example.cms.controller.dto.InterestListDto;
import com.example.cms.controller.exceptions.StudentNotFoundException;
import com.example.cms.controller.exceptions.UniversityNotFoundException;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.All_universitiesRepository;
import com.example.cms.model.repository.InterestListRepository;
import com.example.cms.model.repository.QsRepository;
import com.example.cms.model.repository.StudentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController

public class InterestListController {

    @Autowired
    private final InterestListRepository repository;

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Autowired
    private QsRepository qsRepository;
    @Autowired
    private All_universitiesRepository allUniversitiesRepository;

    public InterestListController(InterestListRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/university/collect")
    void collect(@RequestParam("userId") String userId, @RequestParam("qsId") String qsId) {
        Interest_list query = new Interest_list();
        query.setUserId(userId);
        query.setQS_ranking_id(qsId);
        Example<Interest_list> queryWrapper = Example.of(query);
        if (repository.count(queryWrapper) > 0) {
            return;
        }

        Qs_rankings rankings = qsRepository.findById(qsId).orElseGet(null);
        Interest_list interest_list = new Interest_list();
        interest_list.setUserId(userId);
        interest_list.setIsStudent(1);
        interest_list.setQs_rankings(rankings);
        interest_list.setQS_ranking_id(qsId);

        repository.save(interest_list);

        System.out.println(userId + "  " + qsId);
    }

    @GetMapping("/university/interestlist/{StudentId}")
    List<Interest_list> showListForTheStudent(@PathVariable("StudentId") String studentId) {
        Interest_list query = new Interest_list();
        query.setUserId(studentId);
        Example<Interest_list> queryWrapper = Example.of(query);
        List<Interest_list> all = repository.findAll(queryWrapper);
        for (Interest_list item : all) {
            Qs_rankings rankings = qsRepository.findById(item.getQS_ranking_id()).orElseGet(null);
            item.setQs_rankings(rankings);
        }
        return all;
//        return repository.showListForTheStudent(studentId);
    }

    @PostMapping("/university/interestlist/add")
    Interest_list addNewSchool(@RequestBody InterestListDto listDto) {
        Interest_listKey newListKey = new Interest_listKey();
        Interest_list newList = new Interest_list();
        newListKey.setInterestListOrder(repository.count()+1);
        newListKey.setStudentId(listDto.getStudentId());
        newList.setListKey(newListKey);
        newList.setComment(listDto.getComment());
        StudentUser newStudent = studentUserRepository.findById(listDto.getStudentId()).orElseThrow(
                () -> new StudentNotFoundException(listDto.getStudentId()));
        All_universities university = allUniversitiesRepository.findById(listDto.getUniversityId()).orElseThrow(
                () -> new UniversityNotFoundException(listDto.getUniversityId()));
        newList.setStudentUser(newStudent);
        newList.setUniversity(university);
        return repository.save(newList);
    }

    @PutMapping("/university/interestlist/comment/{StudentId}")
    Interest_list updateComment(@RequestBody InterestListDto listDto,
                                @PathVariable("StudentId") String studentId) {
        repository.findAll();
        return repository.findById(Long.parseLong(studentId)).map(interest_list -> {
            interest_list.setComment(listDto.getComment());
            return repository.save(interest_list);
        }).orElse(null);
    }

    @DeleteMapping("/university/interestlist/{StudentId}")
    void deleteInterestList (@PathVariable("StudentId") String studentId){
        repository.findAll();
        repository.deleteById(Long.parseLong(studentId));
    }
//        Interest_listKey findKey = new Interest_listKey();
//        findKey.setStudentId(studentId);
//        findKey.setInterestListOrder(order);
//        return repository.findById(findKey).map(interest_list -> {
//            interest_list.setComment(listDto.getComment());
//            return repository.save(interest_list);
//        }).orElseGet(()->{
//            return this.addNewSchool(listDto);
//        });
        //       return null;
//    @DeleteMapping("/university/interestlist/{StudentId}/{Order}")
//    void deleteInterestList (@PathVariable("StudentId") String studentId,@PathVariable("Order") long order){
//        Interest_listKey deleteKey = new Interest_listKey();
//        deleteKey.setInterestListOrder(order);
//        deleteKey.setStudentId(studentId);
//        repository.deleteById(deleteKey);
//    }
}







