package uz.pdp.appstudycenters.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appstudycenters.entity.Contact;
import uz.pdp.appstudycenters.payload.ContactDTO;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.repository.ContactRepository;

import java.util.Optional;

@Service
public class ContactService {
    final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    //  CREATE
    public Result addContact(Contact contactDTO) {
        boolean existsByPhoneNumber = contactRepository.existsByPhoneNumber(contactDTO.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("This Number Already exists", false);
        }
        contactRepository.save(contactDTO);
        return new Result("Contact Added", true);
    }

    //READ
    public Result getContact(Integer pages) {
        Pageable pageable= PageRequest.of(pages,10);
        return new Result("Contacts ",true,contactRepository.findAll(pageable));
    }

    //UPDATE
    public Result editContact(Integer id, ContactDTO contactDTO) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (!optionalContact.isPresent()) {
            return new Result("This Contact Not Found", false);
        }
        boolean existsByPhoneNumber = contactRepository.existsByPhoneNumber(contactDTO.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("This Number Already exists", false);
        }
        Contact contact = optionalContact.get();
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact.setEmail(contactDTO.getEmail());
        contact.setWebPage(contactDTO.getWebPage());
        contact.setTelegramAddress(contactDTO.getTelegramAddress());
        contactRepository.save(contact);
        return new Result("Contact Edited", true);
    }

    //DELETE
    public Result deleteContact(Integer id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (!optionalContact.isPresent()) {
            return new Result("Contact Not Found", false);
        }
        contactRepository.deleteById(id);
        return new Result("Contact Deleted", true);
    }

    //READ BY ID
    public Result getByIdContact(Integer id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (!optionalContact.isPresent()) {
            return new Result("Contact Not Found", false);
        }
    return new Result("Contact ",true,optionalContact.get());
    }

}

