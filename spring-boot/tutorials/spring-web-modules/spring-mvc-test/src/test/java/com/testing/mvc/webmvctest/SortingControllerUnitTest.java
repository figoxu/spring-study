package com.testing.mvc.webmvctest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.mvc.SortingController;
import com.testing.mvc.SortingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SortingController.class)
class SortingControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SortingService sortingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenHelloWorldMethodIsCalled_thenReturnSuccessString() throws Exception {
        mockMvc.perform(get("/"))
          .andExpect(status().isOk());
    }

    @Test
    void whenSortMethodIsCalled_thenReturnSortedArray() throws Exception {
        List<Integer> input = Arrays.asList(5, 3, 8, 1, 9, 2);
        List<Integer> sorted = Arrays.asList(1, 2, 3, 5, 8, 9);

        when(sortingService.sortArray(input)).thenReturn(sorted);

        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(input)))
          .andExpect(status().isOk())
          .andExpect(content().json(objectMapper.writeValueAsString(sorted)));
    }

}