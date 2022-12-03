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
    @Column(name = "times_Id")
    private String times_Id;

    @NotEmpty
    private Integer Times_Rank;

    @NotEmpty
    private String University_Name;

    @Nullable
    private String Country;

    @Nullable
    private Float Teaching_Score;

    @Nullable
    private Float International_Score;

    @Nullable
    private Float Research_Score;

    @Nullable
    private Float Citations_Score;

    @Nullable
    private Float Income_Score;

    @Nullable
    private Float Total_Score;

    @Nullable
    private Float Number_Of_Students;

    @Nullable
    private Float Student_Staff_Ratio;

    @Nullable
    private String International_Student_Ratio;

}
