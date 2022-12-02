package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Comment")
public class Comment {

    @EmbeddedId
    private CommentKey commentKey;


    @ManyToOne
    @MapsId("discussionId")
    @JoinColumn(name = "discussionId")
    private Discussion discussionId;

    @ManyToOne
    @JoinColumn(name = "Commenter")
    private AllUsers commenter;

    @NotEmpty
    private String comment;

}
