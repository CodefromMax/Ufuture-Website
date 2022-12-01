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
    private Long universityId;

    @NotEmpty
    private String uniName;
/*
    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qs_id", referencedColumnName = "times_Id")
    private Qs_rankings qs_rankings;


    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "times_id", referencedColumnName = "times_Id")
    private Times_rankings times_rankings;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cwur_id", referencedColumnName = "cwur_Id")
    private CWUR_rankings cwur_rankings;
*/
}
