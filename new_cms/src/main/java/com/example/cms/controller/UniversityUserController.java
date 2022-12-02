package com.example.cms.controller;

import com.example.cms.controller.exceptions.UniversityNotFoundException;
import com.example.cms.model.entity.StudentUser;
import com.example.cms.model.entity.UniversityUser;
import com.example.cms.model.repository.UniversityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UniversityUserController {
    @Autowired
    private final UniversityUserRepository repository;

    public UniversityUserController(UniversityUserRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/UniversityUsers")
    List<UniversityUser> retrieveAllUniversityUsers() {
        return repository.findAll();
    }

    @GetMapping("/UniversityUsers/{id}")
    UniversityUser retrieveStudentUser(@PathVariable("id") String UniversityUserId) {
        return repository.findById(UniversityUserId)
                .orElseThrow(() -> new UniversityNotFoundException(UniversityUserId));
    }

}
