package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "InterestList")

public class Interest_list implements Serializable {
    /*
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String userId;

    //private Integer isStudent;

    private String qS_ranking_id;

     */
    @EmbeddedId
    private Interest_listKey listKey;

    @Nullable
    private String comment;

    /*
    @ManyToOne
    @MapsId("QS_RANKING_ID")
    @JoinColumn(name = "QS_ranking_id")
    private Qs_rankings qs_rankings;

     */


    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId")
    @JsonIgnoreProperties({"discussion"})
    private StudentUser studentUser;

    @ManyToOne
    @JoinColumn(name = "universityId")
    private All_universities university;


}
