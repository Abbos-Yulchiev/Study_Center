package uz.pdp.appstudycenters.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appstudycenters.entity.Address;
import uz.pdp.appstudycenters.entity.District;
import uz.pdp.appstudycenters.payload.AddressDTO;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.repository.AddressRepository;
import uz.pdp.appstudycenters.repository.DistrictRepository;

import java.util.Optional;

@Service
public class AddressService {

    final AddressRepository addressRepository;
    final DistrictRepository districtRepository;

    public AddressService(AddressRepository addressRepository, DistrictRepository districtRepository) {
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
    }
    //  CREATE
    public Result addAddress(AddressDTO addressDTO){
        boolean exists = addressRepository.existsByStreetNameAndCodeAndDistrictId(addressDTO.getStreetName(), addressDTO.getCode(), addressDTO.getDistrictId());
        if (exists){
            return new Result("Address Already Saved",false);
        }
        Address address=new Address();
        address.setCode(addressDTO.getStreetName());
        address.setCode(addressDTO.getCode());
        Optional<District> optionalDistrict = districtRepository.findById(addressDTO.getDistrictId());
        if (!optionalDistrict.isPresent()){
            return new Result("District Nor Found",false);
        }
address.setDistrict(optionalDistrict.get());
        addressRepository.save(address);
        return new Result("Address Saved",true);
    }
    //READ ALL ADDRESS
    public Result getAddress(Integer pages){
        Pageable pageable= PageRequest.of(pages,10);
    return  new Result("All Address ",true,addressRepository.findAll(pageable));
    }
    //UPDATE
    public Result editAddress(Integer id, AddressDTO addressDTO ){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return  new Result("Address Not Found ",false);
        }
        Address address= optionalAddress.get();
        address.setCode(addressDTO.getStreetName());
        address.setCode(addressDTO.getCode());
        Optional<District> optionalDistrict = districtRepository.findById(addressDTO.getDistrictId());
        if (!optionalDistrict.isPresent()){
            return new Result("District Nor Found",false);
        }
        address.setDistrict(optionalDistrict.get());
        addressRepository.save(address);
        return new Result("Address Edited",true);
    }
    //DELETE
    public Result deleteAddress( Integer id ){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return new Result("Not Found",false);
        }
        addressRepository.deleteById(id);
        return new Result("Deleted",true);
    }
    //READ BY ID
    public Result getByIdAddress( Integer id ){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return new Result("Address Not Found",false);
        }
        return new Result("Address",true,optionalAddress.get());
    }


}
