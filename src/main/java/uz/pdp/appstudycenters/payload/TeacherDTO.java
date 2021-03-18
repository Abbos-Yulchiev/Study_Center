package uz.pdp.appstudycenters.payload;

import lombok.Data;
import uz.pdp.appstudycenters.entity.Contact;

import java.util.List;

@Data
public class TeacherDTO {

    private String firstname;
    private String lastname;
    private Integer age;
    private Boolean gender;
    private Integer addressId;
    private String specialist;
    private List<Contact> contact;
}
