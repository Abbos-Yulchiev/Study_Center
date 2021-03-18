package uz.pdp.appstudycenters.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.payload.AddressDTO;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.service.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
    final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    //  CREATE
    @PostMapping
    public Result addAddress(@RequestBody AddressDTO addressDTO){
    return addressService.addAddress(addressDTO);
    }
    //READ
    @GetMapping
    public Result getAddress(@RequestParam("pages") Integer pages){
return addressService.getAddress(pages);
    }
    //UPDATE
    @PutMapping(value = "/addressId")
    public Result edit(@RequestBody AddressDTO addressDTO,@PathVariable Integer addressId){
return addressService.editAddress(addressId,addressDTO);
    }
    //DELETE
    @DeleteMapping(value = "/{addressId}")
    public Result delete(@PathVariable Integer addressId){
return addressService.deleteAddress(addressId);
    }
    //READ BY ID
    @GetMapping(value = "/{addressId}")
    public Result getById(@PathVariable Integer addressId){
return addressService.getByIdAddress(addressId);
    }
}
