package com.example.cms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiscussionCommentTest {
    /*
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    void getSalary() throws Exception{
        ObjectNode ProfessorJson = objectMapper.createObjectNode();
        ProfessorJson.put("id",9966L);
        ProfessorJson.put("firstName","Shabi");
        ProfessorJson.put("lastName", "LMC");
        ProfessorJson.put("email", "shabiwoshi@shas.com");
        ProfessorJson.put("office","MC1234");
        ProfessorJson.put("salary",999999);
        MockHttpServletResponse response = mockMvc.perform(
                post("/professors")
                        .contentType("application/json").content(ProfessorJson.toString())).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        assertTrue(professorRepository.findById(9966L).isPresent());

        Professor addedProfessor = professorRepository.findById(9966L).get();
        assertEquals(9966L, addedProfessor.getId());
        assertEquals("Shabi", addedProfessor.getFirstName());
        assertEquals("LMC", addedProfessor.getLastName());
        assertEquals("shabiwoshi@shas.com", addedProfessor.getEmail());
        assertEquals("MC1234", addedProfessor.getOffice());
        assertEquals(999999, addedProfessor.getSalary());
    }
    @Test
    void getSalaryLow3W() throws Exception{
        ObjectNode ProfessorJson = objectMapper.createObjectNode();
        ProfessorJson.put("id",9977L);
        ProfessorJson.put("firstName","Sddhabi");
        ProfessorJson.put("lastName", "LMddC");
        ProfessorJson.put("email", "shabidfwoshi@shas.com");
        ProfessorJson.put("office","MC1234");
        ProfessorJson.put("salary",20);
        MockHttpServletResponse response = mockMvc.perform(
                post("/professors")
                        .contentType("application/json").content(ProfessorJson.toString())).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        assertTrue(professorRepository.findById(9977L).isPresent());

        Professor addedProfessor = professorRepository.findById(9977L).get();
        assertEquals(9977L, addedProfessor.getId());
        assertEquals("Sddhabi", addedProfessor.getFirstName());
        assertEquals("LMddC", addedProfessor.getLastName());
        assertEquals("shabidfwoshi@shas.com", addedProfessor.getEmail());
        assertEquals("MC1234", addedProfessor.getOffice());
        assertEquals(30000, addedProfessor.getSalary());
    }

*/
}

