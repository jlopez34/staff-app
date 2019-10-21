package com.softplan.staff.repository;

import com.softplan.staff.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends MongoRepository<Person, String> {

    @Query("{ 'CPF' : ?0 }")
    Person findByCPF(String CPF);

}
