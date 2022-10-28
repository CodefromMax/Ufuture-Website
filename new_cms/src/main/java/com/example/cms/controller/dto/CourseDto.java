package com.example.cms.controller.dto;

import com.example.cms.controller.exceptions.ProfessorNotFoundException;
import com.example.cms.model.entity.Course;
import com.example.cms.model.entity.Professor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.cms.model.repository.CourseRepository;
import com.example.cms.model.repository.ProfessorRepository;

@Getter
@Setter
public class CourseDto {
    private String code;
    private String name;
    private Long professorId;

//rows

}