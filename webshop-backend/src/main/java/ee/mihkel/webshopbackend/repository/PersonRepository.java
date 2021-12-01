package ee.mihkel.webshopbackend.repository;

import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
