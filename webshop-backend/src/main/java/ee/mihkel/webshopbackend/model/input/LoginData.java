package ee.mihkel.webshopbackend.model.input;

import lombok.Data;

@Data
public class LoginData {
    private String email;
    private String password;
}
