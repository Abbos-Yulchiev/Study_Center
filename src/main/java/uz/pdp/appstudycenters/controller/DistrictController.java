package uz.pdp.appstudycenters.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.payload.DistrictDTO;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.service.DistrictService;

@RestController
@RequestMapping(value = "/district")
public class DistrictController {

    final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }
    //  CREATE
    @PostMapping
    public Result addDistrict(@RequestBody DistrictDTO districtDTO){
       return districtService.addDistrict(districtDTO);
    }
    //READ
    @GetMapping
    public Result getDistrict(@RequestParam("pages") Integer pages){
return districtService.getDistrict(pages);
    }
    //UPDATE
    @PostMapping(value = "edit/districtId")
    public Result edit(@RequestBody DistrictDTO districtDTO,@PathVariable Integer districtId){
       return districtService.editDistrict(districtId,districtDTO);
    }
    //DELETE
    @GetMapping(value = "delete/{districtId}")
    public Result delete(@PathVariable Integer districtId){
return districtService.deleteDistrict(districtId);
    }
    //READ BY ID
    @GetMapping(value = "byId/{districtId}")
    public Result getById(@PathVariable Integer districtId){
return districtService.getById(districtId);
    }
}
