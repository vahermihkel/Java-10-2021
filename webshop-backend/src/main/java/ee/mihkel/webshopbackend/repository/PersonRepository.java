package ee.mihkel.webshopbackend.repository;

import ee.mihkel.webshopbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PersonRepository extends JpaRepository<Person, String> {

    Person findByEmail(String email);
}
