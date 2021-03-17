package uz.pdp.appstudycenters.controller;


import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.entity.Region;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.service.RegionService;

import java.util.List;

@RestController
@RequestMapping(value = "/region")
public class RegionController {

    final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(value = "/get")
    public List<Region> getRegion() {

        return regionService.getRegion();
    }

    @PostMapping(value = "/upload")
    public Result addRegion(@RequestBody Region region) {

        return regionService.addRegion(region);
    }

    @PutMapping(value = "/edit/{regionId}")
    public Result editRegion(@PathVariable Integer regionId, @RequestBody Region region) {

        return regionService.editRegion(regionId, region);
    }

    @DeleteMapping(value = "/delete/{regionId}")
    public Result deleteRegion(@PathVariable Integer regionId) {

        return regionService.deleteRegion(regionId);
    }
}
