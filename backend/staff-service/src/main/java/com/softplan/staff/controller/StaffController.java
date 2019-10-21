package com.softplan.staff.controller;

import com.softplan.staff.exception.StaffNotFoundExpection;
import com.softplan.staff.model.Person;
import com.softplan.staff.service.StaffService;
import com.softplan.staff.wrapper.PersonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/staff")
public class StaffController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private StaffService staffService;

    private final StaffService repository;

    private final StaffResourceAssembler assembler;

    StaffController(StaffService staffService, StaffService repository, StaffResourceAssembler assembler) {
        this.staffService = staffService;
        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestMapping(value = "/persons/", method = RequestMethod.POST)
    public ResponseEntity<?> createPerson_v1(@Valid @RequestBody Person person)
            throws URISyntaxException {
        LOGGER.info("Consuming service createPerson::::" + person.toString());
        Resource<Person> savedPerson = assembler.toResource(repository.savePerson(person));

        return ResponseEntity
                .created(new URI(savedPerson.getId().expand().getHref()))
                .body(savedPerson);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public Resource<?> retrivePerson(@PathVariable("id") String personId) {
        LOGGER.info("Consuming service retrivePerson:::: Person [" + personId + "]");
        Person person = staffService.getPersonById(personId);

        Optional<Person> optionalPerson = Optional.ofNullable(person);

        optionalPerson.orElseThrow(() -> new StaffNotFoundExpection(personId));

        return assembler.toResource(person);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@RequestBody Person person, @PathVariable("id") String personId)
            throws URISyntaxException {
        LOGGER.info("Consuming service updatePerson:::: Person [" + personId + "]");
        Person updatedPerson = staffService.updatePerson(personId, person);

        Optional<Person> personOptional = Optional.ofNullable(updatedPerson);

        personOptional.orElseThrow(() -> new StaffNotFoundExpection(personId));

        Resource<Person> resource = assembler.toResource(updatedPerson);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }


    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public Resources<Resource<Person>> retriveAllPersons() {
        LOGGER.info("Consuming service retriveAllPersons::::");
        List<Resource<Person>> persons = staffService.getAllPersons().stream().map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(persons, linkTo(methodOn(StaffController.class).retriveAllPersons()).withSelfRel());

    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deletePerson(@PathVariable String id) {
        LOGGER.info("Consuming service deletePerson::::");
        staffService.removePerson(id);

        return ResponseEntity.noContent().build();
    }

}
