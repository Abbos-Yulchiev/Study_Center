package uz.pdp.appstudycenters.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    private Integer age;

    private Boolean gender;

    @ManyToOne
    private Address address;

    private String specialist;

    @OneToOne
    private Contact contact;
}
