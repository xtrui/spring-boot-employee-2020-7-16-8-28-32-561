package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    public static List<Employee> employees = new ArrayList<>();
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(Integer page, Integer pageSize, String gender) {
        if (page != null && pageSize != null) {
            return employeeService.getEmployees(page, pageSize);
        } else if (gender != null) {
            return employeeService.getEmployees(gender);
        }

        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployees(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        Employee addEmployee = employeeService.addEmployee(employee);
        return addEmployee;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee2) {
        employee2.setId(id);
        return employeeService.update(employee2);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteByID(id);
    }

}
