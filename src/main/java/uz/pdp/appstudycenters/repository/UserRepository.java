package uz.pdp.appstudycenters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appstudycenters.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {


    @Query(value = "SELECT count('phone_number') FROM contact\n" +
            "join sys_user su on contact.contact_id = su.id\n" +
            "where phone_number = ?", nativeQuery = true)
    Integer findUserByPhoneNumber(String phone_number);
}
