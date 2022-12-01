package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "UniversityUser")
public class UniversityUser {

    @Id
    @NotEmpty
    private String UniversityId;

    @NotEmpty
    private String UniversityName;

    @NotEmpty
    private String UniversityPassword;
}
