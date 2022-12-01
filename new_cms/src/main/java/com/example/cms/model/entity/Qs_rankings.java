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
@Table(name="QSRanking")

public class Qs_rankings {

    @Id
    @NotEmpty
    private String QS_ranking_id;

    @NotEmpty
    private String Qs_rank;

    @NotEmpty
    private String Institution_Name;

    @NotEmpty
    private String Location;

    @NotEmpty
    private String Academic_Reputation_Score;

    @NotEmpty
    private String Employer_Reputation_Score;
    @NotEmpty
    private String Faculty_Student_Score;

    @NotEmpty
    private String Citations_per_Faculty_Score;

    @NotEmpty
    private String International_Faculty_Score;

    @NotEmpty
    private String International_Students_Score;

    @NotEmpty
    private String International_Research_Network_Score;

    @NotEmpty
    private String Employment_Outcomes_Score;

    @NotEmpty
    private String Overall_Score;

    /*
    @OneToOne(mappedBy = "qs_rankings")
    private All_universities qsToUniversity;

*/
}
