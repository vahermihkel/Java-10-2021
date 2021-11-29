package ee.mihkel.webshopbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    // OneToOne
    private Person person;

    // ManyToOne
    @ManyToOne
    private List<Item> orderItems;
}
