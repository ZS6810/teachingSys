package com.teach.teachingsys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teach.teachingsys.dto.SubmissionDetailResponse;
import com.teach.teachingsys.service.AssignmentSubmissionBusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssignmentSubmissionBusinessController.class)
public class AssignmentSubmissionBusinessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentSubmissionBusinessService submissionBusinessService;

    @Test
    public void getSubmissionDetail_ShouldReturnSuccess() throws Exception {
        SubmissionDetailResponse mockResponse = SubmissionDetailResponse.builder()
                .submissionId(1L)
                .assignmentTitle("Test Assignment")
                .studentName("Student A")
                .build();

        given(submissionBusinessService.getSubmissionDetail(anyLong())).willReturn(mockResponse);

        mockMvc.perform(get("/api/assignment-submissions/business/1/detail")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.submissionId").value(1))
                .andExpect(jsonPath("$.data.assignmentTitle").value("Test Assignment"));
    }
}
