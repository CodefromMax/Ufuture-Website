package com.example.cms;

import com.example.cms.model.entity.Interest_list;
import com.example.cms.model.entity.Interest_listKey;
import com.example.cms.model.repository.All_universitiesRepository;
import com.example.cms.model.repository.InterestListRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InterestListTest {


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private InterestListRepository interestListRepository;

	@Autowired
	private StudentUserRepository studentUserRepository;

	@Autowired
	private All_universitiesRepository universitiesRepository;
//Test for Create, Read, Update, Delete
	@Test
	void CRUDforInterestList() throws Exception{
		//Create

		ObjectNode listJson = objectMapper.createObjectNode();
		listJson.put("studentId", "SU0001");
		listJson.put("comment", "The university is good");
		listJson.put("universityId", "AU001");
		listJson.put("listOrder", "1");
		listJson.put("type", 1);

		MockHttpServletResponse response_interestList = mockMvc.perform(post("/university/interestlist/add").
				contentType("application/json").
				content(listJson.toString())).
				andReturn().getResponse();

		assertEquals(200, response_interestList.getStatus());
		
		Interest_listKey listKey = new Interest_listKey();
		listKey.setStudentId("SU0001");
		listKey.setInterestListOrder("1");
		assertTrue(interestListRepository.findById(listKey).isPresent());
		Interest_list addedList = interestListRepository.findById(listKey).get();
		assertEquals(200, response_interestList.getStatus());

		assertEquals("SU0001", addedList.getListKey().getStudentId());
		assertEquals("1", addedList.getListKey().getInterestListOrder());
		assertEquals("The university is good", addedList.getComment());

		assertEquals("AU001", addedList.getUniversity().getUniversityId());
		assertEquals(1,addedList.getType());

		//Read

		MockHttpServletResponse responseGet = mockMvc.perform(get("/university/interestlist/SU0001"))
				.andReturn().getResponse();


		assertTrue(interestListRepository.findById(listKey).isPresent());

		assertEquals(200, responseGet.getStatus());
		assertEquals("SU0001", addedList.getListKey().getStudentId());
		assertEquals("1", addedList.getListKey().getInterestListOrder());
		assertEquals("The university is good", addedList.getComment());
		assertEquals("AU001", addedList.getUniversity().getUniversityId());
		assertEquals(1,addedList.getType());

		ObjectNode listUpdateJson = objectMapper.createObjectNode();
		listUpdateJson.put("comment", "The university is good and awesome");

		//Update

		MockHttpServletResponse response_update = mockMvc.perform(put("/university/interestlist/comment/SU0001/1").
						contentType("application/json").
						content(listUpdateJson.toString())).andReturn().getResponse();

		Interest_listKey listKeyUpdate = new Interest_listKey();
		listKeyUpdate.setStudentId("SU0001");
		listKeyUpdate.setInterestListOrder("1");
		assertTrue(interestListRepository.findById(listKeyUpdate).isPresent());
		Interest_list addedListUpdate = interestListRepository.findById(listKeyUpdate).get();
		assertEquals(200, response_update.getStatus());
		assertEquals("SU0001", addedListUpdate.getListKey().getStudentId());
		assertEquals("1", addedListUpdate.getListKey().getInterestListOrder());
		assertEquals("The university is good and awesome", addedListUpdate.getComment());
		assertEquals("AU001", addedListUpdate.getUniversity().getUniversityId());
		assertEquals(1,addedListUpdate.getType());

		//Delete

		MockHttpServletResponse response_Delete = mockMvc.perform(
						delete("/university/interestlist/SU0001/1").
								contentType("application/json"))
				.andReturn().getResponse();
		Interest_listKey keyDelete = new Interest_listKey();
		keyDelete.setStudentId("SU0001");
		keyDelete.setInterestListOrder("1");

		assertEquals(200, response_Delete.getStatus());
		assertTrue(interestListRepository.findById(keyDelete).isEmpty());


	}


}


