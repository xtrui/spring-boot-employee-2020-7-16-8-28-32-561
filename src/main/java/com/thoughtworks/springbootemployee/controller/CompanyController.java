package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    static List<Company> companies = new ArrayList<>();
    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanies(Integer page, Integer pageSize) {
        if (page != null && pageSize != null) {
            return companyService.findByPage(page, pageSize);
        }
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable int id) {

        return companyService.getCompany(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesOfCompany(@PathVariable int id) {
        return companyService.getEmployees(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Company updateCompany(@PathVariable int id, @RequestBody Company company2) {
        return companyService.updateCompany(company2);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteById(id);
//        return companies.remove(company);
    }


}
