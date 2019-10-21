package com.softplan.staff.controller;

import com.softplan.staff.model.Person;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


@Component
public class StaffResourceAssembler implements ResourceAssembler<Person, Resource<Person>> {

    @Override
    public Resource<Person> toResource(Person person) {

        return new Resource<>(person,
                linkTo(methodOn(StaffController.class).retrivePerson(person.getId())).withSelfRel(),
                linkTo(methodOn(StaffController.class).retriveAllPersons()).withRel("all_persons"));
    }
}
