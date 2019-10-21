package com.softplan.staff.service.impl;

import com.softplan.staff.model.Person;
import com.softplan.staff.repository.StaffRepository;
import com.softplan.staff.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Autowired
    StaffRepository staffRepository;

    @Override
    public Person savePerson(Person person) {
        Person personResposta = null;
        try {
            if (getPersonByCPF(person.getCPF()) == null) {
                if (StringUtils.isEmpty(person.getId())) {
                    person.setId(UUID.randomUUID().toString());
                    person.setCreatedDate(Instant.now());
                }
                personResposta = staffRepository.save(person);
            }
        } catch (Exception ex) {
            LOGGER.debug("ERROR SAVE PERSON");
        }

        return personResposta;
    }

    @Override
    public Person updatePerson(String idPessoa, Person person) {
        Person personBD = this.getPersonById(idPessoa);
        personBD.setEmail(person.getEmail());
        personBD.setNacionalidade(person.getNacionalidade());
        personBD.setNaturalidade(person.getNaturalidade());
        personBD.setDataNacimento(person.getDataNacimento());
        return staffRepository.save(personBD);
    }

    @Override
    public Person getPersonByCPF(String CPF) {
        return staffRepository.findByCPF(CPF);
    }

    @Override
    public Person getPersonById(String idPessoa) {
        Optional<Person> pessoaOptional = staffRepository.findById(idPessoa);
        return pessoaOptional.get();
    }

    @Override
    public void removePerson(String idPessoa) {
        staffRepository.delete(staffRepository.findById(idPessoa).get());
    }

    @Override
    public List<Person> getAllPersons() {
        return staffRepository.findAll();
    }

}
