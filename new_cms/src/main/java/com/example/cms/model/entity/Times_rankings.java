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
@Table(name="TimesRankings")
public class Times_rankings {

    @Id
    @NotEmpty
    private String times_Id;

    @NotEmpty
    private int Times_Rank;

    @NotEmpty
    private String University_Name;

    @Nullable
    private String Country;

    @Nullable
    private float Teaching_Score;

    @Nullable
    private float International_Score;

    @Nullable
    private float Research_Score;

    @Nullable
    private float Citations_Score;

    @Nullable
    private float Income_Score;

    @Nullable
    private float Total_Score;

    @Nullable
    private long Number_Of_Students;

    @Nullable
    private float Student_Staff_Ratio;

    @Nullable
    private String International_Student_Ratio;

/*
    @OneToOne(mappedBy = "times_rankings")
    private All_universities timesUniversity;
    */
}
