package com.thoughtworks.springbootemployee.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    List<Employee> employees = new ArrayList<>();
    Company company;

    @BeforeEach
    void setUp() {
        companyRepository.deleteAll();
        employees.add(new Employee("alibaba1", 20, "man", 6000.0, 1));
        employees.add(new Employee("alibaba2", 19, "man", 8000.0, 1));
        company = new Company(0, 2, null, "alibaba");
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
    void should_return_companies_when_findByPage_given_page() throws Exception {
        //given
        companyRepository.save(company);
//        company.setCompanyName("Tencent");
//        company.setId(0);
//        companyRepository.save(company);
        //when
        mockMvc.perform(get("/companies?page=1&pageSize=2"))
                .andExpect(jsonPath("$[0].companyName").value("alibaba"));
        //then
    }

    @Test
    void should_return_company_when_add_company_given_company() throws Exception {
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(company)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName").value("alibaba"));
        //then
    }

    @Test
    void should_return_company_when_update_company_given_company() throws Exception {
        companyRepository.save(company);
        employeeRepository.save(employees.get(0));
        company.setCompanyName("tencent");
        mockMvc.perform(put("/companies/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(company)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName").value("tencent"));
    }

    @Test
    void should_return__when_delete_by_id_given_id() throws Exception {
        //given
        companyRepository.save(company);
        //when
        mockMvc.perform(delete("/companies/{id}", 1))
                .andExpect(status().isNoContent());
        //then
    }

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }
}
