package com.burak.article.api;

import com.burak.article.api.controller.AritceController;
import com.burak.article.api.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AritceController.class)
class ApiApplicationTests {
    @MockBean
    private ArticleService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String ADMIN_ENCODED_USER_PASS = "Basic YWRtaW46MTIz";
    private static final String STANDART_ENCODED_USER_PASS = "Basic c3RhbmRhcnQ6MTIz";

    @Test
    public void adminShouldAccessStatistics() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Arrays.asList(new String("Basic YWRtaW46MTIz")));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/article/statistics")
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void standartUserShouldNotAccessStatistics() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Arrays.asList(new String("Basic c3RhbmRhcnQ6MTIz")));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/article/statistics")
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isForbidden())
                .andReturn();

    }


}
