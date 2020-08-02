package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyDTO;
import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.beans.BeanUtils;

public class CompanyMapper {
    public CompanyDTO toCompanyDTO(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        if (company.getId() != null) {
            companyDTO.setId(company.getId());
        }
        if (company.getCompanyName() != null) {
            companyDTO.setCompanyName(company.getCompanyName());
        }
        if (company.getEmployees() != null) {
            companyDTO.setEmployees(company.getEmployees());
        }
        if (company.getEmployeesNums() != null) {
            companyDTO.setEmployeesNums(company.getEmployeesNums());
        }
        return companyDTO;
    }

    public CompanyDTO toCompanyDtoByBeanUtils(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        BeanUtils.copyProperties(company, companyDTO);
        return companyDTO;
    }
}
