package ee.mihkel.webshopbackend.service;

import ee.mihkel.webshopbackend.model.input.EveryPayResponse;
import ee.mihkel.webshopbackend.model.output.EveryPayData;
import ee.mihkel.webshopbackend.model.output.EveryPayLink;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;

@Log4j2
@Service
public class PaymentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${everypay.url}")
    private String everyPayUrl;

    @Value("${everypay.username}")
    private String everyPayUsername;

    @Value("${everypay.accountname}")
    private String everyPayAccountname;

    @Value("${everypay.authorization}")
    private String authorization;

    @Value("${everypay.customerurl}")
    private String customerurl;

    public EveryPayLink getPaymentLink(double amount, Long orderId) {
//        String url = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";

        EveryPayData everyPayData = new EveryPayData();
        everyPayData.setApi_username(everyPayUsername);
        everyPayData.setAccount_name(everyPayAccountname);
        everyPayData.setAmount(amount);
        everyPayData.setOrder_reference(orderId);
        everyPayData.setTimestamp(new Date());
        everyPayData.setNonce(everyPayUsername + new Date());
        everyPayData.setCustomer_url(customerurl);


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<EveryPayData> entity = new HttpEntity<>(everyPayData, headers);

        EveryPayLink everyPayLink = new EveryPayLink();
        everyPayLink.setLink(null);
        try {
            ResponseEntity<EveryPayResponse> responseEntity =
                    restTemplate.exchange(everyPayUrl, HttpMethod.POST,entity, EveryPayResponse.class);
            if (responseEntity.getBody() != null && responseEntity.getBody().getPayment_link() != null) {
                String paymentLink = responseEntity.getBody().getPayment_link();
                everyPayLink.setLink(paymentLink);
            } else {
                log.error("Could not get Link from EveryPay response {}",orderId);
            }
        } catch (RestClientException e) {
            log.error("Could not execute EveryPay request {} {}",orderId, e.getMessage());
        }

        return everyPayLink;
    }
}
