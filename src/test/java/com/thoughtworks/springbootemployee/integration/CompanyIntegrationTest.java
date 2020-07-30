package com.thoughtworks.springbootemployee.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CompanyRepository companyRepository;
    List<Employee> employees = new ArrayList<>();
    Company company;

    @BeforeEach
    void setUp() {

        employees.add(new Employee(1, "alibaba1", 20, "man", 6000.0));
        employees.add(new Employee(2, "alibaba2", 19, "man", 8000.0));
        company = new Company(0, 2, employees, "alibaba");
    }

    @Test
    void should_return_companies_when_get_companies_given_nothing() throws Exception {
        //given
        companyRepository.save(company);
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].companyName").value("alibaba"));
    }

    @Test
    void should_return_company_when_add_company_given_conpany() throws Exception {
        //given
//        companyRepository.save(company);
//        company.setCompanyName("Tencent");
//        companyRepository.save(company);
        //when
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(company)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName").value("alibaba"));
        //then
    }

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }
}
