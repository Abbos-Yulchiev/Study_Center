package uz.pdp.appstudycenters.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.entity.Company;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.service.CompanyService;

@RestController
@RequestMapping(value = "/companyCategory")
public class CompanyCategoryController {

    final CompanyService companyService;

    public CompanyCategoryController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PutMapping(value = "/upload")
    public Result addNewCompany(@RequestBody Company company){

        return companyService.addNewCompany(company);
    }

    /*Get a Company by Id*/
    @GetMapping(value = "/get/{companyId}")
    public Result getCompany(@PathVariable Integer companyId){

        return companyService.getCompany(companyId);
    }

    /*Getting Company pages*/
    @GetMapping(value = "/get")
    public Page<Company> getCompanies(@RequestParam Integer page){

        return companyService.getCompanies(page);
    }

    @PutMapping(value = "/edit/{companyId}")
    public Result editCompany(@PathVariable Integer companyId, @RequestBody Company company){

        return companyService.editCompany(companyId, company);
    }

    @DeleteMapping(value = "/delete/{companyId}")
    public Result deleteCompany(@PathVariable Integer companyId){

        return companyService.deleteCompany(companyId);
    }

}
