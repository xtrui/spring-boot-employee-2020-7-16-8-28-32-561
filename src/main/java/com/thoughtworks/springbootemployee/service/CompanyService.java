package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotExistException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;


    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(int id) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) {
            throw new NotExistException("this company doesn't exist");
        }

        return company;
    }

    public List<Employee> getEmployees(int id) {

        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) {
            throw new NotExistException("this company doesn't exist");
        }
        return employeeRepository.findByCompanyId(company.getId());
    }

    public Company addCompany(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("you didn't give any company");
        }
        return companyRepository.save(company);
    }

    public Company updateCompany(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("you didn't give any company");
        }
        Company existedCompany = companyRepository.findById(company.getId()).orElse(null);
        if (existedCompany == null) {
            throw new NotExistException("this company doesn't exist");
        } else {
            if (company.getCompanyName() != null) {
                existedCompany.setCompanyName(company.getCompanyName());
            }
            if (company.getEmployees() != null) {
                existedCompany.setEmployees(company.getEmployees());
            }
            if (company.getEmployeesNums() != 0) {
                existedCompany.setEmployeesNums(company.getEmployeesNums());
            }
        }
        return companyRepository.save(existedCompany);
    }

    public List<Company> findByPage(int page, int pageSize) {
        return companyRepository.findAll(PageRequest.of(--page, pageSize)).toList();
    }

    public void deleteById(int id) {
        companyRepository.deleteById(id);
    }
}
