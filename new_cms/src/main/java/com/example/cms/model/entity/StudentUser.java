package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "StudentUser")
public class StudentUser {

    @Id
    @NotEmpty
    private String StudentId;

    @NotEmpty
    private String StudentName;

    @NotEmpty
    private String StudentPassword;
    /*
    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Interest_List_Id", referencedColumnName = "list_Id")
    private Interest_list interestList  = new Interest_list();
*/
    @Nullable
    @OneToMany(mappedBy = "participatedStudent")
    @JsonIgnore
    private List<Discussion> discussion = new ArrayList<Discussion>();

    /*
    @Nullable
    @OneToMany(mappedBy = "studentUser")
    @JsonIgnore
    private List<Interest_list> interestList = new ArrayList<Interest_list>();
    
     */


}
