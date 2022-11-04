package com.example.cms.controller;

import com.example.cms.model.entity.Interest_list;
import com.example.cms.model.repository.InterestListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController

public class InterestListController {

    @Autowired
    private final InterestListRepository repository;

    public InterestListController(InterestListRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/university/interestlist/showall")
    List<Interest_list> showlist() {return repository.showAllList();}

    @PostMapping("/university/interestlist/add")
    Interest_list createList(@RequestBody Interest_list newUni) {
        return repository.save(newUni);
    }

    @PutMapping("/university/interestlist/comment/{id}")
    Interest_list updateComment(@RequestBody Interest_list newLst, @PathVariable("id") long ListId) {
        return repository.findById(ListId)
                .map(interestlist -> {
                    interestlist.setComment(newLst.getComment());
                    return repository.save(interestlist);
                })
                .orElseGet(() -> {
                    newLst.setListId(ListId);
                    return repository.save(newLst);
                });
    }
    @DeleteMapping("/university/interestlist/{id}")
    void deleteInterestList(@PathVariable("id") Long DeleteItem) {
        repository.deleteById(DeleteItem);
        }
    }






