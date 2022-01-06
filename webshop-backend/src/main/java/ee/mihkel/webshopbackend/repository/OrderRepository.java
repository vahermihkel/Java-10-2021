package ee.mihkel.webshopbackend.repository;

import ee.mihkel.webshopbackend.model.Order;
import ee.mihkel.webshopbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrdersByPersonEquals(Person person);
}
