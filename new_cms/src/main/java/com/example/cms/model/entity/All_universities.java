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
@Table(name="universities")

public class All_universities {
    @Id
    @NotEmpty

    private String universityId;

    @NotEmpty
    private String uniName;


//
//    @ManyToOne
//    @Nullable
//    @JoinColumn(name="qsRanking")
//    private String qsrankings;
//
//    @ManyToOne
//    @Nullable
//    @JoinColumn(name="timesRanking")
//    private String timesrankings;
//
//    @ManyToOne
//    @Nullable
//    @JoinColumn(name="cwurRanking")
//    private String cwurrankings;
//
//

}
