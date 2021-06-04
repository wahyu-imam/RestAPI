package com.infosys.learning.service;

import com.infosys.learning.dto.PersonRequest;
import com.infosys.learning.model.Person;
import com.infosys.learning.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.UnaryOperator;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public String createPerson(Person person){
        Person existPerson = personRepository.findByEmail(person.getEmail());
        if (existPerson != null){
            return "Email is already exist";
        }
        personRepository.save(person);
        return "Success insert data!";
    }

    public String updatePerson(Person person){
        Person existPerson = personRepository.findById(person.getId());
        System.out.println(person.getId());
        if (existPerson == null){
            return null;
        }
        personRepository.save(person);
        return "Success to change data!";
    }

    public List<Person> findAllPerson(){
        return personRepository.findAll();
    }

    public String deletePersonById(int id){
        Person person = new Person();
        person.setId(id);
        Person existPerson = personRepository.findById(person.getId());
        if (existPerson == null){
            return null;
        }
        personRepository.deleteById(existPerson.getId());
        return "Data successfully deleted!";
    }

    public PersonRequest findPersonById(int id){
        Person person = new Person();
        person.setId(id);
        Person existPerson = personRepository.findById(person.getId());
        if (existPerson == null){
            return null;
        }
        PersonRequest personRequest = new PersonRequest();
        personRequest.setId(existPerson.getId());
        personRequest.setNama(existPerson.getNama());
        personRequest.setEmail(existPerson.getEmail());
        return personRequest;
    }
}
