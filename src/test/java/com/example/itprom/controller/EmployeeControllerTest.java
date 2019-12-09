package com.example.itprom.controller;

import com.example.itprom.domain.Employee;
import com.example.itprom.dto.EmployeeDto;
import com.example.itprom.service.EmployeeService;
import com.example.itprom.utils.EmployeeMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static com.example.itprom.TestData.employee;
import static com.example.itprom.TestData.employeeDtoList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Before("")
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void getAllEmployeesTest() throws Exception {
        List<EmployeeDto> expected = employeeDtoList();

        when(employeeService.getAllEmployees()).thenReturn(expected);

        MvcResult result = mvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        List<EmployeeDto> actual = Arrays.asList(deserialize(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                EmployeeDto[].class));

        assertThat(actual.containsAll(expected));
    }

    @Test
    void saveTest() throws Exception {
        Employee actual = employee();
        EmployeeDto actualDto = EmployeeMapper.map(actual);
        when(employeeService.update(any(EmployeeDto.class))).thenReturn(actual);

        mvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(actualDto))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private static <X> String json(X result) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

    private <X> X deserialize(String json, Class<X> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, type);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}