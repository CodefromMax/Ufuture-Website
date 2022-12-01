package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="Discussion")
public class Discussion {

    @Id
    @NotEmpty
    private Long discussionId;

    @NotEmpty
    private String discussionContent;

    /*

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "StudentId")
    private StudentUser participatedStudent;
*/
}
