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

    //NA strings for rankings, use many to one for all rankings
    @Id
    @NotEmpty
    private String universityId;

    @NotEmpty
    private String uniName;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "qs_id")
    private Qs_rankings qs_rankings;


    @Nullable
    @ManyToOne
    @JoinColumn(name = "cwur_id")
    private CWUR_rankings cwur_rankings;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "times_id")
    private Times_rankings times_rankings;


}
