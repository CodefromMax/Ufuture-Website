package com.example.cms;

import com.example.cms.model.entity.Discussion;
import com.example.cms.model.entity.Interest_list;
import com.example.cms.model.entity.Interest_listKey;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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


    @Test
    void CRUDforDiscussionComment() throws Exception {
        //Create
        ObjectNode listJson = objectMapper.createObjectNode();
        listJson.put("discussionContent", "Is this the best university");
        listJson.put("userId", "SU0001");
        listJson.put("universityName", "Massachusetts Institute of Technology (MIT)");

        MockHttpServletResponse response_create = mockMvc.perform(post("/discussion/post").
                        contentType("application/json").
                        content(listJson.toString())).
                andReturn().getResponse();
        assertEquals(200, response_create.getStatus());

        assertTrue(discussionRepository.findById(10L).isPresent());
        Discussion addedDiscussion = discussionRepository.findById(10L).get();
        assertEquals("SU0001", addedDiscussion.getUser().getUserId());
        assertEquals(10L, addedDiscussion.getDiscussionId());
        assertEquals("Is this the best university", addedDiscussion.getDiscussionContent());
        assertEquals("AU001", addedDiscussion.getUniversity().getUniversityId());

        //Read
        MockHttpServletResponse response_read = mockMvc.perform(get("/discussion/Stanford University")).
                andReturn().getResponse();
        Discussion discussionForGet = discussionRepository.findById(3L).get();
        assertTrue(discussionRepository.findById(3L).isPresent());
        assertEquals(200, response_read.getStatus());
        assertEquals("SU0003", discussionForGet.getUser().getUserId());
        assertEquals(3L, discussionForGet.getDiscussionId());
        assertEquals("Is this a good university", discussionForGet.getDiscussionContent());
        assertEquals("AU003", discussionForGet.getUniversity().getUniversityId());

        //Update
        //Follow Interest List, also generate the same thing for comment

    }
}

