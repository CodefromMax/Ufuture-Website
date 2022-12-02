package com.example.cms.controller;

import com.example.cms.controller.dto.UserNameDto;
import com.example.cms.controller.exceptions.UniversityNotFoundException;
import com.example.cms.controller.vo.UserVO;
import com.example.cms.model.entity.StudentUser;
import com.example.cms.model.entity.UniversityUser;
import com.example.cms.model.repository.StudentUserRepository;
import com.example.cms.model.repository.UniversityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
public class UniversityUserController {
    @Autowired
    private final UniversityUserRepository repository;

    @Autowired
    private final StudentUserRepository studentUserRepository;

    public UniversityUserController(UniversityUserRepository repository, StudentUserRepository studentUserRepository) {
        this.repository = repository;
        this.studentUserRepository = studentUserRepository;
    }
    @GetMapping("/UniversityUsers")
    List<UniversityUser> retrieveAllStudentUsers() {
        return repository.findAll();
    }
    @GetMapping("/UniversityUsers/{id}")
    UniversityUser retrieveStudentUser(@PathVariable("id") String UniversityUserId) {
        return repository.findById(UniversityUserId)
                .orElseThrow(() -> new UniversityNotFoundException(UniversityUserId));
    }

    @PostMapping("/UniversityUsers/login")
    public UserVO login(@RequestBody UserNameDto userNameDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UniversityUser universityUser = new UniversityUser();
        universityUser.setUserId(userNameDto.getUserName());
        universityUser.setPassword(userNameDto.getPassword());
        Example<UniversityUser> example = Example.of(universityUser);
        if (repository.count(example) > 0) {
            UserVO userVO = new UserVO();
            userVO.setIsStudent(0);
            userVO.setUserId(universityUser.getUserId());
            session.setAttribute("user", userVO);
            return userVO;
        }

        StudentUser studentUser = new StudentUser();
        studentUser.setUserId(userNameDto.getUserName());
        studentUser.setPassword(userNameDto.getPassword());
        Example<StudentUser> studentUserExample = Example.of(studentUser);
        if (studentUserRepository.count(studentUserExample) > 0) {
            UserVO userVO = new UserVO();
            userVO.setIsStudent(1);
            userVO.setUserId(studentUser.getUserId());
            session.setAttribute("user", userVO);
            return userVO;
        }

        return  null;

    }


}
