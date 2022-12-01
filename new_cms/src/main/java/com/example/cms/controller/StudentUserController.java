package com.example.cms.controller;

import com.example.cms.controller.exceptions.StudentNotFoundException;
import com.example.cms.model.entity.StudentUser;
import com.example.cms.model.repository.StudentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class StudentUserController {
    @Autowired
    private final StudentUserRepository repository;

    public StudentUserController(StudentUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/StudentUsers")
    List<StudentUser> retrieveAllStudentUsers() {
        return repository.findAll();
    }

    @PostMapping("/StudentUsers")
    StudentUser createStudentUser(@RequestBody StudentUser newStudentUser) {
        return repository.save(newStudentUser);
    }

    @GetMapping("/StudentUsers/{id}")
    StudentUser retrieveStudentUser(@PathVariable("id") Long StudentUserId) {
        return repository.findById(StudentUserId)
                .orElseThrow(() -> new StudentNotFoundException(StudentUserId));
    }

    @DeleteMapping("/StudentUsers/{id}")
    void deleteStudentUser(@PathVariable("id") Long StudentUserId) {
        repository.deleteById(StudentUserId);
    }

    @GetMapping("/StudentUsers/search/{searchstring}")
    List<StudentUser> searchStudentUser(@PathVariable("searchstring") String searchString) {
        return repository.search(searchString);
    }

    @GetMapping("/StudentUsers/top")
    List<StudentUser> retrieveTopStudentUsers() {
        return repository.findTopStudentUsers();
    }
}
