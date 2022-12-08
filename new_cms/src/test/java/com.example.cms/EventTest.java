package com.example.cms;

import com.example.cms.controller.exceptions.StudentNotFoundException;
import com.example.cms.model.entity.All_universities;
import com.example.cms.model.entity.Events;
import com.example.cms.model.entity.Interest_listKey;
import com.example.cms.model.entity.UniversityUser;
import com.example.cms.model.repository.EventsRepository;
import com.example.cms.model.repository.StudentUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentUserRepository studentRepository;

    @Autowired
    private EventsRepository eventsRepository;

    @Test
    void addEventTest() throws Exception{
        //Create
        ObjectNode eventJson = objectMapper.createObjectNode();
        eventJson.put("eventName", "School is Back testing");
        eventJson.put("eventDate", "2023-09-23 06:48:50");
        eventJson.put("location", "zoom.ca");
        eventJson.put("universityUserId", "UU0010");
        eventJson.put("university", "AU020");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/events/post").
                                contentType("application/json").
                                content(eventJson.toString()))
                .andReturn().getResponse();

        // assert HTTP code of response is 200 OK
        assertEquals(200, response.getStatus());

        assertTrue(eventsRepository.findById(6L).isPresent());
        Events addedEvent = eventsRepository.findById(6L).get();

        // Assert the details of the students are correct
        assertEquals(6L, addedEvent.getEventCode());
        assertEquals("School is Back testing", addedEvent.getEventName());
        assertEquals("2023-09-23 06:48:50", addedEvent.getEventDate());
        assertEquals("zoom.ca", addedEvent.getLocation());
        assertEquals("UU0010", addedEvent.getUniversityUser().getUserId());
    }

    @Test
    void updateEventTest() throws Exception{
        //Update
        ObjectNode eventJson = objectMapper.createObjectNode();
        eventJson.put("eventName", "We love MIE350");
        eventJson.put("eventDate", "2023-09-23 06:48:51");
        eventJson.put("location", "Bahen 1130");

        MockHttpServletResponse response = mockMvc.perform(
                        put("/events/5").
                                contentType("application/json").
                                content(eventJson.toString()))
                .andReturn().getResponse();

        // assert HTTP code of response is 200 OK
        assertEquals(200, response.getStatus());

        // Assert student with id 8888 exists in our repository and then get the student object
        assertTrue(eventsRepository.findById(5L).isPresent());
        Events addedEvent = eventsRepository.findById(5L).get();

        // Assert the details of the students are correct
        assertEquals(5L, addedEvent.getEventCode());
        assertEquals("We love MIE350", addedEvent.getEventName());
        assertEquals("2023-09-23 06:48:51", addedEvent.getEventDate());
        assertEquals("Bahen 1130", addedEvent.getLocation());
        assertEquals("UU0002", addedEvent.getUniversityUser().getUserId());
    }


    @Test
    void deleteEventTest() throws Exception{

        MockHttpServletResponse response = mockMvc.perform(
                        delete("/discussion/1").
                                contentType("application/json"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(studentRepository.findById("1L").isEmpty());
    }

}
