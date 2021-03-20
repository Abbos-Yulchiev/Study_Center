package uz.pdp.appstudycenters.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appstudycenters.entity.Address;
import uz.pdp.appstudycenters.entity.Contact;
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
        //O'ZGARTIRILDI
        Optional<Contact> optionalContact = contactRepository.findById(userDTO.getContactId());

        User user = new User();

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setGender(userDTO.getGender());
        user.setAddress(optionalAddress.get());
        user.setAge(userDTO.getAge());
        user.setContact(optionalContact.get());
        userRepository.save(user);
        return new Result("New User successfully added.", true, user.getId());

    }

    public Page<User> getUsers(Integer page){

        Pageable pageable = PageRequest.of(page,15);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    public Result getUser(Integer userId){

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()){
            return new Result("Invalid user id", false);
        }
        return new Result("User result" , true, optionalUser);
    }

    public Result editUser(Integer userId, UserDTO userDTO){

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            return new Result("Invalid user Id", false);

        Optional<Address> optionalAddress = addressRepository.findById(userDTO.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Invalid Address id", false);
        Optional<Contact> optionalContact = contactRepository.findById(userDTO.getContactId());

        User user = optionalUser.get();

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setAge(userDTO.getAge());
        user.setAddress(optionalAddress.get());
        //o'zgartirildi
        user.setContact(optionalContact.get());
        user.setGender(userDTO.getGender());
        userRepository.save(user);
        return new Result("User edited.", true, user.getId());
    }
    public Result deleteUser(@PathVariable Integer userId){

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            return new Result("Invalid user Id", false);
        userRepository.deleteById(userId);
        return new Result("User deleted", true);
    }

}
