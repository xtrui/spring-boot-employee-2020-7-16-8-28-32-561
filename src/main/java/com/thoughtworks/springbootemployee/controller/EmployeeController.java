package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    public static List<Employee> employees;

    @GetMapping
    public List<Employee> getEmployee(Integer page, Integer pageSize, String gender) {
        employees.add(new Employee(1, "alibaba1", 20, "male", 6000.0));
        employees.add(new Employee(2, "alibaba2", 19, "male", 8000.0));
        if (page != null && pageSize != null) {
            return employees.subList(page * --pageSize, page * pageSize);
        } else if (gender != null) {
            return employees.stream().filter(employee -> employee.getGender() == gender).collect(Collectors.toList());
        }
        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employees.stream().filter(employee -> employee.getId() == 1).findAny().orElse(null);
    }

    @PostMapping
    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }
//    todo
//    @PutMapping("{id}")
//    public Employee

    @DeleteMapping("{id}")
    public boolean deleteEmployee(@PathVariable int id) {
        Employee employee = employees.stream().filter(employee1 -> employee1.getId() == id).findAny().orElse(null);
        return employees.remove(employee);
    }

}
