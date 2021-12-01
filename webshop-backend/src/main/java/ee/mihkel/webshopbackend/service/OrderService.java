package ee.mihkel.webshopbackend.service;

import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.model.Order;
import ee.mihkel.webshopbackend.model.Person;
import ee.mihkel.webshopbackend.repository.ItemRepository;
import ee.mihkel.webshopbackend.repository.OrderRepository;
import ee.mihkel.webshopbackend.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class OrderService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    OrderRepository orderRepository;

    public List<Item> getDatabaseItems(List<Item> items) {
        List<Item> itemsFromDb = new ArrayList<>();
        for (Item i: items) {
            Item itemFound = itemRepository.findById(i.getId()).get();
            itemsFromDb.add(itemFound);
        }
        return itemsFromDb;
    }

    public double getOrderSum(List<Item> items) {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public Long saveOrder(double totalAmount, List<Item> items, String personCode) {
        Order order = new Order();
//        Person person = personRepository.findById(personCode).get();
//        order.setPerson(person);
        order.setOrderItems(items);
        order.setAmount(totalAmount);
        log.info("Alustasin salvestamist");
        Order submittedOrder = orderRepository.save(order);
        log.info(submittedOrder);
        return submittedOrder.getId();
    }
}
