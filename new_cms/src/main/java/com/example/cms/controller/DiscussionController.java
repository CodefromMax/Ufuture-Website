package com.example.cms.controller;


import com.example.cms.controller.dto.DiscussionDto;
import com.example.cms.controller.exceptions.*;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.All_universitiesRepository;
import com.example.cms.model.repository.DiscussionRepository;
import com.example.cms.model.repository.StudentUserRepository;
import com.example.cms.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DiscussionController {

    @Autowired
    private final DiscussionRepository repository;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private All_universitiesRepository universityRepository;


    public DiscussionController(DiscussionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/discussion/showall")
    List<Discussion> showDiscussion() {return repository.findAll();}
/*
    @PostMapping("/discussion/post")
    Discussion createDiscussion(@RequestBody DiscussionDto DiscussionDto){
        Discussion newDiscussion = new Discussion();
        newDiscussion.setDiscussionId(DiscussionDto.getDiscussionId());
        newDiscussion.setDiscussionContent(DiscussionDto.getDiscussionContent());
        User user = UserRepository.findById(DiscussionDto.getUserId()).orElseThrow(
                () -> new UserNotFoundException(DiscussionDto.getUserId()));
        All_universities involvedUniversity = universityRepository.findUniByName(DiscussionDto.getUniversityName()).orElseThrow(
                () -> new UniversityNotFoundException(DiscussionDto.getUniversityName()));
        newDiscussion.setParticipatedStudent(studentUser);
        newDiscussion.setUniversity(involvedUniversity);
        return repository.save(newDiscussion);

    }
    @PostMapping("/discussion/university/post")

    @PutMapping("/discussion/{disId}")
    Discussion updateContents(@RequestBody DiscussionDto DiscussionDto, @PathVariable("disId") Long discussionId){
        return repository.findById(discussionId)
                .map(discussion -> {
                    discussion.setDiscussionContent(DiscussionDto.getDiscussionContent());
                    StudentUser student = studentUserRepository.findById(DiscussionDto.getUserId()).orElseThrow(
                            () -> new StudentNotFoundException(DiscussionDto.getUserId()));
                    discussion.setParticipatedStudent(student);
                    return repository.save(discussion);
                })
                .orElseGet(() -> {
                    return this.StudentcreateDiscussion(DiscussionDto);
                    });
    }
    @DeleteMapping("/discussion/{disId}")
    void deleteDiscussion(@PathVariable("disId") Long discussionId) {repository.deleteById(discussionId);
    }
 */
}

