package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployees(int page, int pageSize) {

        return employeeRepository.findAll(PageRequest.of(page, pageSize)).toList();
    }

    public List<Employee> getEmployees(String gender) {
        Employee employee = new Employee();
        employee.setGender(gender);
        return employeeRepository.findAll(Example.of(employee));
    }

    public Employee getEmployee(int ID) {
        return employeeRepository.findById(ID).orElse(null);
    }
}
