package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public class CompanyDTO {
    private Integer id;
    private Integer employeesNums;
    private List<Employee> employees;
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getEmployeesNums() {
        return employeesNums;
    }

    public void setEmployeesNums(int employeesNums) {
        this.employeesNums = employeesNums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "id=" + id +
                ", employeesNums=" + employeesNums +
                ", employees=" + employees +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
