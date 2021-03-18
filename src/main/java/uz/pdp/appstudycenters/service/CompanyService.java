package uz.pdp.appstudycenters.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appstudycenters.entity.Company;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.repository.CompanyRepository;

import java.util.Optional;

@Service
public class CompanyService {

    final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Result addNewCompany(@RequestBody Company company) {

        boolean existsByName = companyRepository.existsByName(company.getName());
        if (existsByName)
            return new Result("The company name already exist", false);

        Company newCompany = new Company();

        newCompany.setName(company.getName());
        newCompany.setDescription(company.getDescription());
        newCompany.setAddress(company.getAddress());
        newCompany.setContact(company.getContact());
        companyRepository.save(newCompany);
        return new Result("New company successfully added;", true, newCompany.getId());
    }

    public Page<Company> getCompanies(Integer page) {

        Pageable pageable = PageRequest.of(page, 15);
        return companyRepository.findAll(pageable);
    }

    public Result getCompany(@PathVariable Integer companyId){

        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (!optionalCompany.isPresent())
            return new Result("Invalid company Id", false);

        return new Result("Company:", true, optionalCompany);
    }

    public Result editCompany(@PathVariable Integer companyId, @RequestBody Company company){

        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (!optionalCompany.isPresent())
            return new Result("Invalid company id",false);

        boolean existsByName = companyRepository.existsByName(company.getName());
        if (existsByName)
            return new Result("Company name already exist chose another one", false);

        Company editedCompany = optionalCompany.get();
        editedCompany.setName(company.getName());
        editedCompany.setDescription(company.getDescription());
        editedCompany.setAddress(company.getAddress());
        editedCompany.setContact(company.getContact());
        companyRepository.save(editedCompany);
        return new Result("Company successfully edited;", true, editedCompany.getId());
    }

    public Result deleteCompany(@PathVariable Integer companyId){

        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (!optionalCompany.isPresent())
            return new Result("Invalid company Id", false);

        companyRepository.deleteById(companyId);
        return new Result("Company deleted!", true);
    }
}
