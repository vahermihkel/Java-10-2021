package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.output.EveryPayLink;
import ee.mihkel.webshopbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("payment")
    public EveryPayLink startPayment() {
        EveryPayLink everyPayLink = paymentService.getPaymentLink();
        return everyPayLink;
    }

}
