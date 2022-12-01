package com.example.cms.controller;


import com.example.cms.controller.dto.DiscussionDto;
import com.example.cms.controller.exceptions.DepartmentNotFoundException;
import com.example.cms.controller.exceptions.DiscussionNotFoundException;
import com.example.cms.controller.exceptions.DiscussionNotMatchException;
import com.example.cms.controller.exceptions.StudentNotFoundException;
import com.example.cms.model.entity.Discussion;
import com.example.cms.model.entity.Interest_list;
import com.example.cms.model.entity.StudentUser;
import com.example.cms.model.repository.DiscussionRepository;
import com.example.cms.model.repository.StudentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


public class DiscussionController {

    @Autowired
    private final DiscussionRepository repository;

    @Autowired
    private StudentUserRepository studentRepository;


    public DiscussionController(DiscussionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/discussion/showall")
    List<Discussion> showDiscussion() {return repository.findAll();}

    /*
    @PostMapping("/discussion")
    Discussion createDiscussion(@RequestBody DiscussionDto discussionDto){
        Discussion newDiscussion = new Discussion();
        newDiscussion.setDiscussionId(discussionDto.getDiscussionId());
        newDiscussion.setDiscussionContent(discussionDto.getDiscussionContent());
        Student student = studentRepository.findById(discussionDto.getStudentId()).orElseThrow(()->
                new StudentNotFoundException(discussionDto.getStudentId()));
        newDiscussion.setParticipatedStudent(student);
        return repository.save(newDiscussion);
    }

    @PutMapping("/discussion/{disId}")
    Discussion updateContents(@RequestBody DiscussionDto discussionDto, @PathVariable("disId") Long discussionId){
        return repository.findById(discussionId)
                .map(discussion -> {
                    discussion.setDiscussionContent(discussionDto.getDiscussionContent());
                    Student student = studentRepository.findById(discussionDto.getStudentId()).orElseThrow(
                            () -> new StudentNotFoundException(discussionDto.getStudentId()));
                    discussion.setParticipatedStudent(student);
                    return repository.save(discussion);
                })
                .orElseGet(() -> {
                    return this.createDiscussion(discussionDto);
                    });
    }
    /*
    @DeleteMapping("/discussion/{disId}/{studentId}")
    void deleteDiscussion(@PathVariable("disId") long disId, @PathVariable("studentId") long studentId){
        repository.findById(disId).map(selectedDiscussion -> {
            if (selectedDiscussion.getParticipatedStudent().getId() == studentId) {
                repository.deleteById(disId);
            } else {
                new DiscussionNotMatchException(disId);
            }
            return null;
        }).orElseGet(()->{
            return new DiscussionNotFoundException(disId);
        });
    }
     */
}

