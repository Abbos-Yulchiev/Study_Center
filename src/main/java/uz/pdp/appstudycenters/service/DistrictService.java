package uz.pdp.appstudycenters.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appstudycenters.entity.District;
import uz.pdp.appstudycenters.entity.Region;
import uz.pdp.appstudycenters.payload.DistrictDTO;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.repository.DistrictRepository;
import uz.pdp.appstudycenters.repository.RegionRepository;

import java.util.Optional;

@Service
public class DistrictService {

    final DistrictRepository districtRepository;
    final RegionRepository regionRepository;

    public DistrictService(DistrictRepository districtRepository, RegionRepository regionRepository) {
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
    }
    //  CREATE
    public Result addDistrict(DistrictDTO districtDTO){
        boolean exists = districtRepository.existsByNameAndCodeAndRegion_Id(districtDTO.getName(), districtDTO.getCode(), districtDTO.getRegionId());
        if (exists){
            return new Result("District Already added",false);
        }
        Optional<Region> optionalRegion = regionRepository.findById(districtDTO.getRegionId());
        if (!optionalRegion.isPresent()){
            return new Result("Region Not Found",false);
        }
        District district=new District();
        district.setName(districtDTO.getName());
        district.setCode(districtDTO.getCode());
        district.setRegion(optionalRegion.get());
        districtRepository.save(district);
        return new Result("District Saved",true);
    }
    //READ
    public Result getDistrict(Integer pages){
        Pageable pageable= PageRequest.of(pages,10);
        return new Result("All District",true, districtRepository.findAll(pageable));
    }
    //UPDATE
    public Result editDistrict(Integer id,DistrictDTO districtDTO  ){
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (!optionalDistrict.isPresent()){
            return new Result("District not found",false);
        }

        District district= optionalDistrict.get();
        district.setName(districtDTO.getName());
        district.setCode(districtDTO.getCode());
        Optional<Region> optionalRegion = regionRepository.findById(districtDTO.getRegionId());
        if (!optionalRegion.isPresent()){
            return new Result("Region Not Found",false);
        }
        district.setRegion(optionalRegion.get());
        districtRepository.save(district);
        return new Result("District Edited",true);
    }
    //DELETE
    public Result deleteDistrict( Integer id ){
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (!optionalDistrict.isPresent()){
            return new Result("District Not Found",false);
        }
        districtRepository.deleteById(id);
        return new Result(" District Deleted",true);
    }
    //READ BY ID
    public Result getById( Integer id ){
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (!optionalDistrict.isPresent()){
            return new Result(" District Not Found",false);
        }
        return new Result(" District ",true,districtRepository.findById(id));
    }



}
