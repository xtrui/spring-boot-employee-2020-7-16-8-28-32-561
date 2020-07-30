package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class EmployeeServiceTest {
    Employee employee = new Employee(1, "alibaba1", 20, "male", 6000.0);
    Employee employee2 = new Employee(2, "alibaba2", 19, "male", 8000.0);
    List<Employee> employees = new ArrayList<>();
    EmployeeRepository mockEmployeeRepository = mock(EmployeeRepository.class);
    EmployeeService employeeService = new EmployeeService(mockEmployeeRepository);

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

    @Test
    void should_employees_of_page_when_get_employees_given_page() {
        // given
        PageImpl<Employee> employeePage = new PageImpl<>(this.employees);
        given(mockEmployeeRepository.findAll(PageRequest.of(1, 2))).willReturn(employeePage);
        // when
        List<Employee> employees = employeeService.getEmployees(1, 2);
        // then
        assertEquals(this.employees, employees);
    }

    @Test
    void should_employee_of_male_when_get_employees_by_gender_given_male() {
        // given
        String gender = "male";
        given(mockEmployeeRepository.findByGender(any())).willReturn(employees);
        // when

        List<Employee> employeeList = employeeService.getEmployees("male");
        // then
        assertEquals(employees, employeeList);
    }

    @Test
    void should_return_employee_when_get_employee_given_ID() {
        // given
        given(mockEmployeeRepository.findById(any())).willReturn(Optional.of(employee2));
        // when
        Employee employee = employeeService.getEmployee(2);
        // then
        assertEquals(employee2, employee);
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() {
        // given
        given(mockEmployeeRepository.save(employee)).willReturn(employee);
        // when
        Employee createdEmployee = employeeService.addEmployee(employee);
        // then
        assertEquals(employee, createdEmployee);
    }

    @Test
    void should_return_employee_when_update_given_employee() {
        // given
        given(mockEmployeeRepository.findById(any())).willReturn(Optional.of(employee));
        given(mockEmployeeRepository.save(employee)).willReturn(employee);
        // when
        Employee updatedEmployee = employeeService.update(employee);
        // then
        assertEquals(employee, updatedEmployee);
    }

    @Test
    void should_return__when_delete_by_ID_given_ID() {
        // given

        // when
        employeeService.deleteByID(1);
        // then
        verify(mockEmployeeRepository, times(1)).deleteById(1);
    }
}
