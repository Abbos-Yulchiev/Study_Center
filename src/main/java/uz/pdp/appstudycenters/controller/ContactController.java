package uz.pdp.appstudycenters.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appstudycenters.entity.Contact;
import uz.pdp.appstudycenters.payload.ContactDTO;
import uz.pdp.appstudycenters.payload.Result;
import uz.pdp.appstudycenters.service.ContactService;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {

final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    //  CREATE
    @PostMapping
    public Result addContact(@RequestBody Contact contactDTO){
return contactService.addContact(contactDTO);
    }
    //READ
    @GetMapping
    public Result getContacts(@RequestParam("pages") Integer pages){
return contactService.getContact(pages);
    }
    //UPDATE
    @PostMapping(value = "/{contactId}")
    public Result editContact(@RequestBody ContactDTO contactDTO,@PathVariable Integer contactId ){
return  contactService.editContact(contactId,contactDTO);
    }
    //DELETE
    @RequestMapping(value = "delete/{contactId}")
    public Result deleteContact(@PathVariable Integer contactId){
return contactService.deleteContact(contactId);
    }
    //READ BY ID
    @GetMapping(value = "/{contactId}")
    public Result getByIdContact(@PathVariable Integer contactId){
return contactService.getByIdContact(contactId);
    }
}
