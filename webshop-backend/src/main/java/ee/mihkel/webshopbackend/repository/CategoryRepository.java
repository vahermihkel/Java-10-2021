package ee.mihkel.webshopbackend.repository;

import ee.mihkel.webshopbackend.model.Category;
import ee.mihkel.webshopbackend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
