package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;



    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(int ID) {
        return companyRepository.findById(ID).get();
    }

    public List<Employee> getEmployees(int ID) {
        return companyRepository.findById(ID).get().getEmployees();
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Company company) {
        Company existedCompany = companyRepository.findById(company.getId()).orElse(null);
        if (existedCompany == null) {
            return null;
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
        return companyRepository.save(company);
    }

    public Page<Company> findByPage(PageRequest pageRequest) {

        return companyRepository.findAll(pageRequest);
    }
}
