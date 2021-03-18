package uz.pdp.appstudycenters.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appstudycenters.entity.Address;
import uz.pdp.appstudycenters.entity.User;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.payload.UserDTO;
import uz.pdp.appstudycenters.repository.AddressRepository;
import uz.pdp.appstudycenters.repository.ContactRepository;
import uz.pdp.appstudycenters.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final ContactRepository contactRepository;
    final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, ContactRepository contactRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
    }

    public Result addUser(@RequestBody UserDTO userDTO) {

        Optional<Address> optionalAddress = addressRepository.findById(userDTO.getAddressId());
        if (!optionalAddress.isPresent()) {
            return new Result("Invalid Address id", false);
        }

        User user = new User();

        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setGender(userDTO.getGender());
        user.setAddress(optionalAddress.get());
        user.setAge(user.getAge());
        user.setContact(user.getContact());
        userRepository.save(user);
        return new Result("New User successfully added.", true, user.getId());

    }
}
