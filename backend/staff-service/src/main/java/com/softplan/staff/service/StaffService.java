package com.softplan.staff.service;

import com.softplan.staff.model.Person;

import java.util.List;

/**
 *
 */
public interface StaffService {

    Person savePerson(Person person);

    Person updatePerson(String idPessoa, Person person);

    Person getPersonByCPF(String CPF);

    Person getPersonById(String idPessoa);

    void removePerson(String idPessoa);

    List<Person> getAllPersons();
}
