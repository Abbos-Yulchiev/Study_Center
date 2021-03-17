package uz.pdp.appstudycenters.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @OneToMany
    @JoinColumn(name = "address_id")
    private List<Address> address;

    @OneToMany
    @JoinColumn(name = "contact_id")
    private List<Contact> contact;
}
