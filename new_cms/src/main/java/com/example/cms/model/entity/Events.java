package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter

@Table(name = "Events")
public class Events {

    @Id
    private Long eventCode;

    @NotEmpty
    private String eventName;

    @NotEmpty
    private String eventDate;

    @NotEmpty
    private String location;

    @ManyToOne
    @JoinColumn (name = "createrId")
    private UniversityUser universityUser;

    @ManyToOne
    @JoinColumn(name = "university")
    private All_universities university;



}