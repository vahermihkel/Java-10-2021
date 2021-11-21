package ee.mihkel.webshopbackend.repository;

import ee.mihkel.webshopbackend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
