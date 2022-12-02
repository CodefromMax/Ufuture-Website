package com.example.cms.controller;


import com.example.cms.controller.dto.DiscussionDto;
import com.example.cms.controller.exceptions.*;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.AllUsersRepository;
import com.example.cms.model.repository.All_universitiesRepository;
import com.example.cms.model.repository.DiscussionRepository;
import com.example.cms.model.repository.StudentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class DiscussionController {

    @Autowired
    private final DiscussionRepository repository;

    @Autowired
    private AllUsersRepository UserRepository;

    @Autowired
    private All_universitiesRepository universityRepository;


    public DiscussionController(DiscussionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/discussion/showall")
    List<Discussion> showDiscussion() {return repository.findAll();}

    @PostMapping("/discussion/post")
    Discussion createDiscussion(@RequestBody DiscussionDto DiscussionDto){
        Discussion newDiscussion = new Discussion();
        newDiscussion.setDiscussionId(repository.count()+1);
        newDiscussion.setDiscussionContent(DiscussionDto.getDiscussionContent());
        AllUsers user = UserRepository.findById(DiscussionDto.getUserId()).orElseThrow(
                () -> new UserNotFoundException(DiscussionDto.getUserId()));
        All_universities involvedUniversity = universityRepository.findUniByName(DiscussionDto.getUniversityName());
        newDiscussion.setUser(user);
        newDiscussion.setUniversity(involvedUniversity);
        return repository.save(newDiscussion);

    }


    @PutMapping("/discussion/{disId}")
    Discussion updateContents(@RequestBody DiscussionDto DiscussionDto, @PathVariable("disId") Long discussionId){
        return repository.findById(discussionId)
                .map(discussion -> {
                    discussion.setDiscussionContent(DiscussionDto.getDiscussionContent());
                    return repository.save(discussion);
                })
                .orElseGet(() -> {
                    return this.createDiscussion(DiscussionDto);
                    });
    }
    @DeleteMapping("/discussion/{disId}")
    void deleteDiscussion(@PathVariable("disId") Long discussionId) {repository.deleteById(discussionId);
    }
}

