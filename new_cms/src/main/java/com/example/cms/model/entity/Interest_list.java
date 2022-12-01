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
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "InterestList")

public class Interest_list {

    @EmbeddedId
    private Interest_listKey listKey;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId")
    @JsonIgnoreProperties({"discussion"})
    private StudentUser studentUser;

    @ManyToOne
    @JoinColumn(name = "universityId")
    private All_universities university;

    @Nullable
    private String comment;

}
