package com.example.cms.model.entity;

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

    /*
    @ManyToOne
    @Nullable
    @JoinColumn(name = "discussionId")
    private Discussion discussionId;
*/
    @NotEmpty
    private String comment;


}
