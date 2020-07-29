package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {
    Employee employee = new Employee(1, "alibaba1", 20, "male", 6000.0);
    Employee employee2 = new Employee(2, "alibaba2", 19, "male", 8000.0);
    List<Employee> employees = new ArrayList<>();
    EmployeeRepository mockEmployeeRepository = mock(EmployeeRepository.class);
    EmployeeService employeeService = new EmployeeService();

    @BeforeEach
    void setUp() {
        employees.add(employee);
        employees.add(employee2);
    }

    @Test
    void should_return_employees_when_get_employees() {
        // given
        given(mockEmployeeRepository.findAll()).willReturn(employees);
        // when
        List<Employee> employeeList = employeeService.getEmployees();
        // then
        assertEquals(employees, employeeList);
    }


}
