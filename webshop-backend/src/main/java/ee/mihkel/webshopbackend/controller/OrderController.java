package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.model.Person;
import ee.mihkel.webshopbackend.model.output.EveryPayLink;
import ee.mihkel.webshopbackend.repository.PersonRepository;
import ee.mihkel.webshopbackend.service.OrderService;
import ee.mihkel.webshopbackend.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;

    @Autowired
    PersonRepository personRepository;

    // Miks 2 korda tellimuse andmebaasi päring
    // 1. Maksmise ajal tahan teada juba tellimuse numbrit
    //              ja selle genereerib mulle hibernate
    // 2. Kui läks midagi makse ajal valesti, klient ütleb et on maksnud
    //          siis on jälg olemas mis kell andmebaasi pandi

    @PostMapping("payment")
    public EveryPayLink startPayment(@RequestBody List<Item> items) {
        // andmebaasi enne maksmist tellimuse koos toodetega maksmata staatuses
        log.info("STARTING PAYMENT");
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person person = personRepository.findByEmail(email);
        EveryPayLink everyPayLink = new EveryPayLink();
        if (person != null) {
            String personCode = person.getPersonCode();
            List<Item> databaseItems = orderService.getDatabaseItems(items);
            double totalAmount = orderService.getOrderSum(databaseItems);
            Long orderId = orderService.saveOrder(totalAmount, databaseItems, personCode);
            log.info("Starting EveryPay payment {}", orderId);
            everyPayLink = paymentService.getPaymentLink(totalAmount, orderId);
            log.info("Fetched EveryPay link {}",everyPayLink);
        }
        return everyPayLink;
    }

    @PostMapping("paid")
    public void changePaymentStatus() {
        // otsin selle tellimuse ja ütlen et on makstud
    }

//    @GetMapping("orders")
//    public List<Order> getOrders() {
//        String email = SecurityContextHolder.getContext()
//                .getAuthentication().getPrincipal().toString();
//        Person person = personRepository.findByEmail(email);
//
    // OrderRepositorysse mis tagastab kõik Orderid Person alusekl
    // Cache -- Kõik Personi Orderid
//    }

}
