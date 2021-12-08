package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.exception.EmailExistsException;
import ee.mihkel.webshopbackend.exception.RegistrationException;
import ee.mihkel.webshopbackend.exception.UserExistsException;
import ee.mihkel.webshopbackend.model.Person;
import ee.mihkel.webshopbackend.model.input.LoginData;
import ee.mihkel.webshopbackend.model.output.AuthData;
import ee.mihkel.webshopbackend.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;

import static java.util.Calendar.HOUR;

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("login")
    public ResponseEntity<Boolean> login(@RequestBody LoginData loginData) {
        boolean res = false;
        if (loginData.getEmail() != null && loginData.getPassword() != null) {
            Person person = personRepository.findByEmail(loginData.getEmail());
            if (person != null) {
                res = person.getPassword().equals(loginData.getPassword());
            }
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<AuthData> signup(@RequestBody Person person) throws RegistrationException, UserExistsException, EmailExistsException {
        try {
            if (personRepository.findById(person.getPersonCode()).isPresent()) {
                throw new UserExistsException();
            }
            if (personRepository.findByEmail(person.getEmail()) != null) {
                throw new EmailExistsException();
            }
            personRepository.save(person);
            AuthData authData = new AuthData();
            authData.setToken("12312312312312");
            authData.setExpirationDate(new Date(new Date().getTime() + 2 * HOUR));
            return new ResponseEntity<>(authData, HttpStatus.OK);
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
}
