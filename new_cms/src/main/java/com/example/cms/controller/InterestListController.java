package com.example.cms.controller;

import com.example.cms.controller.dto.InterestListDto;
import com.example.cms.controller.exceptions.StudentNotFoundException;
import com.example.cms.controller.exceptions.UniversityNotFoundException;
import com.example.cms.model.entity.All_universities;
import com.example.cms.model.entity.Interest_list;
import com.example.cms.model.entity.Interest_listKey;
import com.example.cms.model.entity.StudentUser;
import com.example.cms.model.repository.All_universitiesRepository;
import com.example.cms.model.repository.InterestListRepository;
import com.example.cms.model.repository.StudentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController

public class InterestListController {

    @Autowired
    private final InterestListRepository repository;

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Autowired
    private All_universitiesRepository allUniversitiesRepository;

    public InterestListController(InterestListRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/university/interestlist/{StudentId}")
    List<Interest_list> showListForTheStudent(@PathVariable ("StudentId") String studentId) {return repository.showListForTheStudent(studentId);}

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

    @PutMapping("/university/interestlist/comment/{StudentId}/{Order}")
    Interest_list updateComment(@RequestBody InterestListDto listDto, @PathVariable("StudentId") String studentId, @PathVariable("Order") long order) {
        Interest_listKey findKey = new Interest_listKey();
        findKey.setStudentId(studentId);
        findKey.setInterestListOrder(order);
        return repository.findById(findKey).map(interest_list -> {
            interest_list.setComment(listDto.getComment());
            return repository.save(interest_list);
        }).orElseGet(()->{
            return this.addNewSchool(listDto);
        });
    }
    @DeleteMapping("/university/interestlist/{StudentId}/{Order}")
    void deleteInterestList(@PathVariable("StudentId") String studentId, @PathVariable("Order") long order) {
        Interest_listKey deleteKey = new Interest_listKey();
        deleteKey.setInterestListOrder(order);
        deleteKey.setStudentId(studentId);
        repository.deleteById(deleteKey);
        }
    }






