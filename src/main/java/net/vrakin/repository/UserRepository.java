package net.vrakin.repository;

import net.vrakin.model.Service;
import net.vrakin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByFirstNameAndLastName(String firstName, String lastName);
}
