package ee.mihkel.webshopbackend.repository;

import ee.mihkel.webshopbackend.model.Category;
import ee.mihkel.webshopbackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
