package com.example.cms.controller;

import com.example.cms.controller.dto.CommentDto;
import com.example.cms.controller.exceptions.DiscussionNotFoundException;
import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.AllUsersRepository;
import com.example.cms.model.repository.CommentRepository;
import com.example.cms.model.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CommentController {


    @Autowired
    private final CommentRepository repository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private AllUsersRepository usersRepository;

    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/comment/{discussionId}")
    List<Comment> showCommentForSelectedDiscussion(@PathVariable("discussionId") Long disId){
        return repository.showCommentForDiscussion(disId);
    }

    @PostMapping("/comment/post")
    Comment createNewComment(@RequestBody CommentDto commentDto){
        CommentKey newCommentKey = new CommentKey();
        Comment newComment = new Comment();
        newCommentKey.setCommentorder(repository.countForComment(commentDto.getDiscussionId())+1);
        newCommentKey.setDiscussionId(commentDto.getDiscussionId());
        Discussion discussion = discussionRepository.findById(commentDto.getDiscussionId()).orElseThrow(
                () -> new DiscussionNotFoundException(commentDto.getDiscussionId()));
        AllUsers commenter = usersRepository.findById(commentDto.getCommenterId()).orElseThrow(
                () -> new UserNotFoundException(commentDto.getCommenterId()));
        newComment.setCommentKey(newCommentKey);
        newComment.setDiscussionId(discussion);
        newComment.setCommenter(commenter);
        newComment.setComment(commentDto.getContents());
        return repository.save(newComment);
    }
    @PutMapping("/comment/update/{DiscussionId}/{Order}")
    Comment updateContents(@RequestBody CommentDto commentDto, @PathVariable("DiscussionId") long disId, @PathVariable("Order") long order){
        CommentKey findKey = new CommentKey();
        findKey.setDiscussionId(disId);
        findKey.setCommentorder(order);
        return repository.findById(findKey).map(comment -> {
            comment.setComment(commentDto.getContents());
            return repository.save(comment);
        }).orElseGet(()->{
            return this.createNewComment(commentDto);
        });
    }

    @DeleteMapping("/comment/delete/{DiscussionId}/{Order}")
    void deleteInterestList(@PathVariable("DiscussionId") long disId, @PathVariable("Order") long order) {
        CommentKey deleteKey = new CommentKey();
        deleteKey.setCommentorder(order);
        deleteKey.setDiscussionId(disId);
        repository.deleteById(deleteKey);
    }
}
