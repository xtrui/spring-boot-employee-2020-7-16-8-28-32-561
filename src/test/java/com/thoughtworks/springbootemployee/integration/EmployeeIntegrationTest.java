package com.thoughtworks.springbootemployee.integration;

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
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    MockMvc mockMvc;
    List<Employee> employees = new ArrayList<>();
    Company company;

    @BeforeEach
    void setUp() {
        company = new Company(0, 2, null, "alibaba");
        Company saveCompany = companyRepository.save(company);
        Employee employee1 = new Employee("alibaba1", 20, "man", 6000.0, saveCompany.getId());
        Employee employee2 = new Employee("alibaba2", 19, "man", 8000.0, saveCompany.getId());
        employees.add(employee1);
        employees.add(employee2);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
    }

    @Test
    void should_return_employees_when_get_employees_given_nothing() throws Exception {
        // when

        mockMvc.perform(get("/employees"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_return_employees_when_get_employees_given_page_and_page_size() throws Exception {
        // when
        mockMvc.perform(get("/employees?page=1&pageSize=2"))
                .andExpect(jsonPath("$", hasSize(2)));
        // then

    }

    @Test
    void should_return_employees_when_get_employees_given_gender() throws Exception {
        mockMvc.perform(get("/employees?gender=man"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_return_employee_when_get_employee_given_id() throws Exception {
        // given
        Employee saveEmployee = employeeRepository.save(employees.get(1));
        // when
        mockMvc.perform(get("/employees/" + saveEmployee.getId()))
                .andExpect(jsonPath("$.id").value(saveEmployee.getId()));
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }
}
