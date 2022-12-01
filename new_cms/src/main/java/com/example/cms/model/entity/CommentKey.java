package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

public class CommentKey implements Serializable {
    @Column(name = "DiscussionId")
    private Long DiscussionIdOnComment;

    @Column(name = "courseId")
    private Long StudentIdOnComment ;

    @Override
    public int hashCode(){
        String concatString = String.valueOf(DiscussionIdOnComment.hashCode()) + String.valueOf(StudentIdOnComment.hashCode());
        return concatString.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof CommentKey))
            return false;
        CommentKey other = (CommentKey) o;
        return DiscussionIdOnComment.equals(other.DiscussionIdOnComment) && StudentIdOnComment.equals(other.StudentIdOnComment);
    }
}
