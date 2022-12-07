package com.example.cms;

import com.example.cms.model.entity.*;
import com.example.cms.model.repository.CommentRepository;
import com.example.cms.model.repository.DiscussionRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiscussionCommentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private CommentRepository commentRepository;

    /* @Test
    void getEvent() throws Exception{
        //Read
        MockHttpServletResponse response = mockMvc.perform(get("/discussion/showall")
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals(1l, receivedJson.get("eventCode").longValue());
        assertEquals("School is Back", receivedJson.get("eventName").textValue());
        assertEquals("2023-09-23 06:48:50", receivedJson.get("eventDate").textValue());
        assertEquals("zoom.ca", receivedJson.get("location").textValue());
        assertEquals("UU0001", receivedJson.get("createrId").textValue());
        assertEquals("AU0001", receivedJson.get("university").textValue());
    }*/

    @Test
    void CRUDforDiscussionComment() throws Exception {

        //*********************** Create (post) ***************************
        ObjectNode DisucussionJson = objectMapper.createObjectNode();
        DisucussionJson.put("discussionContent", "Is this a good university");
        DisucussionJson.put("userId", "SU0001");
        DisucussionJson.put("universityName", "Stanford University");

        MockHttpServletResponse response_create = mockMvc.perform(post("/discussion/post").
                        contentType("application/json").
                        content(DisucussionJson.toString())).
                andReturn().getResponse();
        assertEquals(200, response_create.getStatus());

        assertTrue(discussionRepository.findById(10L).isPresent());
        Discussion addedDiscussion = discussionRepository.findById(10L).get();

        assertEquals("Is this a good university", addedDiscussion.getDiscussionContent());
        assertEquals("SU0001", addedDiscussion.getUser().getUserId());
        assertEquals("Stanford University", addedDiscussion.getUniversity().getUniName());

        //*********************** Read (get) ***************************
        /*

        MockHttpServletResponse response_read = mockMvc.perform(get("/discussion/AU0001")).
                andReturn().getResponse();

        Discussion discussionForGet = discussionRepository.findById(1L).get();
        assertTrue(discussionRepository.findById(1L).isPresent());

        assertEquals(200, response_read.getStatus());

        assertEquals(1L, discussionForGet.getDiscussionId());
        assertEquals("Is this a good university", discussionForGet.getDiscussionContent());
        assertEquals("SU0001", discussionForGet.getUser());
        assertEquals("AU003", discussionForGet.getUniversity());

         */

        //*********************** Update ***************************

        ObjectNode DiscussionUpdateJson = objectMapper.createObjectNode();
        DiscussionUpdateJson.put("discussionContent", "The university is good and awesome");

        MockHttpServletResponse response_update = mockMvc.perform(put("/discussion/10").
                contentType("application/json").
                content(DiscussionUpdateJson.toString())).andReturn().getResponse();

        assertTrue(discussionRepository.findById(10L).isPresent());
        Discussion updatedDiscussion = discussionRepository.findById(10L).get();

        assertEquals("The university is good and awesome", updatedDiscussion.getDiscussionContent());
        assertEquals("SU0001", updatedDiscussion.getUser().getUserId());
        assertEquals("Stanford University", updatedDiscussion.getUniversity().getUniName());

        //*********************** Delete ***************************

        MockHttpServletResponse response = mockMvc.perform(
                        delete("/discussion/10").
                                contentType("application/json"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(discussionRepository.findById(10L).isEmpty());
    }
}