package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.Person;
import ee.mihkel.webshopbackend.model.input.LoginData;
import ee.mihkel.webshopbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Boolean> signup(@RequestBody Person person) {
        personRepository.save(person);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
