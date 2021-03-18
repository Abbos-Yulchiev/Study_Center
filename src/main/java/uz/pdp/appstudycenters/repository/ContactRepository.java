package uz.pdp.appstudycenters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appstudycenters.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

}
