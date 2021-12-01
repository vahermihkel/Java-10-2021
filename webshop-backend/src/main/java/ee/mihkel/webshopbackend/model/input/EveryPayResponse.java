package ee.mihkel.webshopbackend.model.input;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class EveryPayResponse {
    private String account_name;
    private String order_reference;
    private String email;
    private String customer_ip;
    private String customer_url;
    private String payment_created_at;
    private BigDecimal initial_amount;
    private BigDecimal standing_amount;
    private String payment_reference;
    private String payment_link;
    private List<PaymentMethod> payment_methods;
    private String api_username;
    private String stan;
    private String fraud_score;
    private String payment_state;
    private String payment_method;
}

@Data
class PaymentMethod {
    private String source;
    private String displayName;
    private String countryCode;
    private String paymentLink;
    private String logoUrl;
}
