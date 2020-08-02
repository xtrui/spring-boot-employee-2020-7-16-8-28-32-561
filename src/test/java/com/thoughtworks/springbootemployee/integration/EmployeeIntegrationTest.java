package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee("alibaba1", 20, "man", 6000.0, 0);
        Employee employee2 = new Employee("alibaba2", 19, "man", 8000.0, 0);
    }

    @Test
    void should__when__given_() {
        // given

        // when

        // then

    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }
}
