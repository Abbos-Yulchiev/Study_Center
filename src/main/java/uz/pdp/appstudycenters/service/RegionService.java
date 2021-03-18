package uz.pdp.appstudycenters.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appstudycenters.entity.Region;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {


    final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Page<Region> getRegion(Integer page) {
        Pageable pageable= PageRequest.of(page,10);
        return regionRepository.findAll(pageable);
    }

    public Result addRegion(@RequestBody Region region) {

        boolean exist = regionRepository.existsByName(region.getName());

        if (exist) {
            return new Result(region.getName() + " - region already exist. Enter another name!", false);
        }
        Region newRegion = new Region();

        newRegion.setName(region.getName());
        newRegion.setCode(region.getCode());
        regionRepository.save(newRegion);
        return new Result("New Region successfully added", true, region.getId());
    }

    public Result editRegion(@PathVariable Integer regionId, @RequestBody Region region) {


        Optional<Region> optionalRegion = regionRepository.findById(regionId);

        if (!optionalRegion.isPresent()) {
            return new Result("Invalid Region Id", false);
        }

        boolean existsByName = regionRepository.existsByName(region.getName());
        if (existsByName){
            return new Result(region.getName() + " - region already exist. Enter another name!", false);

        }

        /*Edit region*/
        Region editedRegion = optionalRegion.get();
        editedRegion.setName(region.getName());
        editedRegion.setCode(region.getCode());
        regionRepository.save(editedRegion);
        return new Result("Region Edited", true, region.getId());
    }
    public Result deleteRegion(@PathVariable Integer regionId) {

        Optional<Region> optionalRegion = regionRepository.findById(regionId);
        if (!optionalRegion.isPresent()) {
            return new Result("Region not found, Invalid Region Id", false);
        }
        regionRepository.deleteById(regionId);
        return new Result("Region Deleted", true, regionId);
    }
    //GET REGION BY ID
    public Result  getRegionById(Integer regionId){
        Optional<Region> optionalRegion = regionRepository.findById(regionId);
        if (!optionalRegion.isPresent()){
            return new Result("Region not found, Invalid Region Id", false);
        }
        return new Result("Region founded", true,regionRepository.findById(regionId));
    }
}

