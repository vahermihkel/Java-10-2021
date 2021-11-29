package ee.mihkel.webshopbackend.service;

import ee.mihkel.webshopbackend.model.output.EveryPayLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class PaymentService {

    @Autowired
    RestTemplate restTemplate;

    public EveryPayLink getPaymentLink() {
        String url = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";

        String api_username = "92ddcfab96e34a5f";
        String account_name = "EUR3D1";
        int amount = 10;
        String order_reference = String.valueOf(Math.floor(Math.random()*99999));
        Date timestamp = new Date();
        String nonce = "92ddcfab96e34a5f" + new Date();
        String customer_url = "https://www.example.com";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "");
//        restTemplate.exchange(url, HttpMethod.POST,);

        EveryPayLink everyPayLink = new EveryPayLink();
        everyPayLink.setLink("");

        return everyPayLink;
    }
}
