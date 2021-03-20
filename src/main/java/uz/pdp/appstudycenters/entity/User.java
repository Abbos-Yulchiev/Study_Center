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
@Table(name = "sys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    @ManyToOne
    private Address address;
    
    @OneToOne
    //O'ZGARTIRILDI
  //  @JoinColumn(name = "contact_id")
    private Contact contact;

    private Integer age;

    private Boolean gender;

}
