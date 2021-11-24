package ee.mihkel.webshopbackend.model;

import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String categoryName;
}
