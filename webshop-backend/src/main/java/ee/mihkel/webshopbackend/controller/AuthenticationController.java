package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.exception.EmailExistsException;
import ee.mihkel.webshopbackend.exception.RegistrationException;
import ee.mihkel.webshopbackend.exception.UserExistsException;
import ee.mihkel.webshopbackend.model.Person;
import ee.mihkel.webshopbackend.model.input.LoginData;
import ee.mihkel.webshopbackend.model.output.AuthData;
import ee.mihkel.webshopbackend.repository.PersonRepository;
import ee.mihkel.webshopbackend.service.JwtBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static java.util.Calendar.HOUR;

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtBuilder jwtBuilder;

    @PostMapping("login")
    public ResponseEntity<AuthData> login(@RequestBody LoginData loginData) {
        if (loginData.getEmail() != null && loginData.getPassword() != null) {
            Person person = personRepository.findByEmail(loginData.getEmail());
            if (person != null) {
                log.info(loginData.getPassword());
                log.info(person.getPassword());
                if (encoder.matches(loginData.getPassword(), person.getPassword()) ||
                        loginData.getPassword().equals(person.getPassword())) {
                    log.info("matchitud");
                    AuthData data = jwtBuilder.createJwtAuthToken(person);
                    return new ResponseEntity<>(data, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("signup")
    public ResponseEntity<Person> signup(@RequestBody Person person) throws RegistrationException, UserExistsException, EmailExistsException {
        try {
            if (personRepository.findById(person.getPersonCode()).isPresent()) {
                throw new UserExistsException();
            }
            if (personRepository.findByEmail(person.getEmail()) != null) {
                throw new EmailExistsException();
            }
            String hashedPassword = encoder.encode(person.getPassword());
            person.setPassword(hashedPassword);
            personRepository.save(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (UserExistsException exception) {
            log.error("Kasutaja registreerimisel on kasutaja juba olemas {}",
                    person.getPersonCode());
            throw new UserExistsException();
        } catch (EmailExistsException exception) {
            log.error("Kasutaja registreerimisel on email juba olemas {}",
                    person.getPersonCode());
            throw new EmailExistsException();
        } catch (Exception exception) {
           log.error("Kasutaja registreerumisel tekkis viga {} {}",
                   person.getPersonCode(), exception.getMessage());
            throw new RegistrationException();
        }

    }

    @PostMapping("logout")
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @GetMapping("validate-token")
    public ResponseEntity<Boolean> validateToken() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
