package org.group02.guitarshop.repository;

import org.group02.guitarshop.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByEmail(String email);
}