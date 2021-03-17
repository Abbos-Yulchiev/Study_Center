package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
