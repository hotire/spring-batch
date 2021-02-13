package com.github.hotire.springbatch.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(InternalJobExecutionController.class)
class InternalJobExecutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void execute() throws Exception {
        // given
        final String jobName = "helloJob";

        // when then
        mockMvc.perform(get("/v1/jobs/{jobName}/execution", jobName)
                                .param("startDate", LocalDateTime.now().toString()))
               .andExpect(status().isOk());
    }
}