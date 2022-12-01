package com.example.cms.model.service;

import com.example.cms.model.repository.StudentUserRepository;
import org.springframework.stereotype.Service;

@Service
public class CmsSerivce {

    public CmsSerivce(StudentUserRepository studentRepository) {
    }
}
