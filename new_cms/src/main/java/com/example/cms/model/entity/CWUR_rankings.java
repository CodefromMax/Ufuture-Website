package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="CWURRankings")
public class CWUR_rankings {

    @Id
    @NotEmpty
    @Column(name = "cwur_Id")
    private String cwur_Id;

    @NotEmpty
    private String Cwur_rank;

    @NotEmpty
    private String Institution_Name;

    @Nullable
    private String Country;

    @Nullable
    private Integer National_rank;

    @Nullable
    private Integer Quality_of_education;

    @Nullable
    private Integer Alumni_Employment;

    @Nullable
    private Integer Quality_of_Faculty;

    @Nullable
    private Integer Publications;

    @Nullable
    private Integer Influence;

    @Nullable
    private Integer citations;

    @Nullable
    private Integer broad_impact;

    @Nullable
    private Integer patents;

    @Nullable
    private Integer score;


}
