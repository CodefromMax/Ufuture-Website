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
    private String cwur_Id;

    @NotEmpty
    private int Cwur_rank;

    @NotEmpty
    private String Institution_Name;

    @Nullable
    private String Country;

    @Nullable
    private int National_rank;

    @Nullable
    private int Quality_of_education;

    @Nullable
    private int Alumni_Employment;

    @Nullable
    private int Quality_of_Faculty;

    @Nullable
    private int Publications;

    @Nullable
    private int Influence;

    @Nullable
    private int citations;

    @Nullable
    private int broad_impact;

    @Nullable
    private int patents;

    @Nullable
    private int score;


   // @OneToOne(mappedBy = "cwur_rankings")
    //private All_universities cwurUniversity;

}
