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
@Table(name = "AllUsers")

public class AllUsers {
    @Id
    @NotEmpty
    private String UserId;

    @NotEmpty
    private String UserName;

    @NotEmpty
    private String UserPassword;
/*
    @NotEmpty
    private String UserType;
 */
}
