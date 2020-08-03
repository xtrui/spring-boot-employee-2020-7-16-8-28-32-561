package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotExistException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CompanyServiceTest {
    List<Company> mockedCompanies = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();
    CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
    EmployeeRepository mockEmployeeRepository = mock(EmployeeRepository.class);
    CompanyService companyService = new CompanyService(mockedCompanyRepository, mockEmployeeRepository);

    @BeforeEach
    void setUp() {
        employees.add(new Employee(1, "alibaba1", 20, "man", 6000.0));
        employees.add(new Employee(2, "alibaba2", 19, "man", 8000.0));
        mockedCompanies.add(new Company(1, 2, employees, "alibaba"));
    }

    @Test
    void should_return_companies_when_get_companies() {
        //given
        given(mockedCompanyRepository.findAll()).willReturn(mockedCompanies);
        //when
        List<Company> companies = companyService.getCompanies();
        //then
        assertEquals(mockedCompanies.size(), companies.size());
    }

    @Test
    void should_return_companies_of_page_when_find_by_page_given_page_and_page_size() {
        // given
        PageImpl<Company> companyPage = new PageImpl<>(mockedCompanies);
        given(mockedCompanyRepository.findAll(PageRequest.of(1, 2))).willReturn(companyPage);
        // when
        Page<Company> page = companyService.findByPage(1, 2);
        // then
        assertEquals(1, page.getSize());
    }

    @Test
    void should_return_right_company_when_get_company_given_company_id() {
        //given
        given(mockedCompanyRepository.findById(any())).willReturn(Optional.of(mockedCompanies.get(0)));
        //when
        Company company = companyService.getCompany(1);
        //then
        assertEquals(mockedCompanies.get(0), company);
    }

    @Test
    void should_throw_not_exist_exception_when_get_company_given_company_id() {
        //given
        given(mockedCompanyRepository.findById(any())).willReturn(Optional.empty());
        //when
        //then
        NotExistException notExistException = assertThrows(NotExistException.class, () -> companyService.getCompany(1));
        assertEquals("this company doesn't exist", notExistException.getMessage());
    }

    @Test
    void should_return_employees_when_get_employees_given_company_id() {
        //given
        given(mockedCompanyRepository.findById(any())).willReturn(Optional.of(mockedCompanies.get(0)));
        //when
        List<Employee> employees = companyService.getEmployees(1);
        //then
        assertEquals(mockedCompanies.get(0).getEmployees(), employees);
    }

    @Test
    void should_return_company_when_add_company_given_company() {
        //given
        given(mockedCompanyRepository.save(mockedCompanies.get(0))).willReturn(mockedCompanies.get(0));
        //when
        Company company = companyService.addCompany(mockedCompanies.get(0));
        //then
        assertEquals(mockedCompanies.get(0), company);
    }

    @Test
    void should_throw_illegal_argument_exception_when_add_company_given_null() {
        //given
        //when
        //then
        RuntimeException exception = assertThrows(IllegalArgumentException.class, () -> companyService.addCompany(null));
        assertEquals("you didn't give any company", exception.getMessage());
    }

    @Test
    void should_return_updated_company_when_update_company_given_company() {
        //given
        given(mockedCompanyRepository.findById(any())).willReturn(Optional.of(mockedCompanies.get(0)));

        given(mockedCompanyRepository.save(any())).willReturn(mockedCompanies.get(0));
        //when
        Company company = companyService.updateCompany(mockedCompanies.get(0));
        //then
        assertEquals(mockedCompanies.get(0), company);
    }

    @Test
    void should_return_null_update_company_given_company_not() {
        //given
        given(mockedCompanyRepository.findById(any())).willReturn(Optional.empty());
        //when
        //then
        NotExistException notExistException = assertThrows(NotExistException.class, () -> companyService.updateCompany(mockedCompanies.get(0)));

        assertEquals("this company doesn't exist", notExistException.getMessage());
    }

    @Test
    void should_return__when_delete_by_ID_given_ID() {
        // given

        // when
        companyService.deleteById(1);
        // then
        verify(mockedCompanyRepository, times(1)).deleteById(1);
    }
}
