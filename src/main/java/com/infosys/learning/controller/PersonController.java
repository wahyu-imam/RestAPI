package com.infosys.learning.controller;

import com.infosys.learning.dto.PersonRequest;
import com.infosys.learning.model.Person;
import com.infosys.learning.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping(value = "/getallperson")
    public ResponseEntity<List<Person>> getAllPerson(){
        try {
            List<Person> findAll = personService.findAllPerson();
            return new ResponseEntity<>(findAll, HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/getall")
    public List<Person> getAll(){
        return personService.findAllPerson();
    }

    @PostMapping(value = "/getpersonbyid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonRequest> getPersonById(@RequestBody(required = true) @Valid Person person){
        PersonRequest personRequest;
        try {
            personRequest = personService.findPersonById(person.getId());
            return new ResponseEntity<>(personRequest, HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/createperson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPerson(@RequestBody(required = true) @Valid Person person){
        String respon;
        try {
            respon = personService.createPerson(person);
            if (respon.equals("Success insert data!")){
                return new ResponseEntity<>("Success insert data!", HttpStatus.CREATED);
            }else if (respon.equals("Email is already exist")){
                return new ResponseEntity<>("Email is already exist", HttpStatus.ALREADY_REPORTED);
            }else {
                return new ResponseEntity<>("Failed insert data!", HttpStatus.FAILED_DEPENDENCY);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Failed insert data!", HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @PostMapping(value = "/updateperson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePerson(@RequestBody(required = true) @Valid Person person){
        String respon;
        try {
            respon = personService.updatePerson(person);
            if (respon.equals("Success to change data!")){
                return new ResponseEntity<>("Success to change data!", HttpStatus.CREATED);
            }
        }catch (NullPointerException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<>("Failed to change data!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/deleteperson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePerson(@RequestBody(required = true) @Valid Person person){
        String respon;
        try {
            respon = personService.deletePersonById(person.getId());
            if (respon.equals("Data successfully deleted!")){
                return new ResponseEntity<>("Data successfully deleted!", HttpStatus.OK);
            }
        }catch (NullPointerException e){
            return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Failed to delete data!", HttpStatus.FAILED_DEPENDENCY);
    }
}
