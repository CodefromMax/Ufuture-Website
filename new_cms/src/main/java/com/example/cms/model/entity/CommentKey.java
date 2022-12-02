package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
@Getter
@Setter
public class CommentKey implements Serializable {

    @Column(name = "commentId")
    private Long Commentorder;

    @Column(name = "discussionId")
    private Long DiscussionId;


    @Override
    public int hashCode(){
        String concatString = String.valueOf(DiscussionId.hashCode()) + String.valueOf(Commentorder.hashCode());
        return concatString.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof CommentKey))
            return false;
        CommentKey other = (CommentKey) o;
        return DiscussionId.equals(other.DiscussionId) && Commentorder.equals(other.Commentorder);
    }
}
