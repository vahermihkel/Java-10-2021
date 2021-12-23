package ee.mihkel.webshopbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {
    @Id
    @NotBlank
    @NotNull
    private String personCode;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String email;
    private String phone;

    @NotBlank
    @NotNull
    private String password;
}
