package com.unitbv.lab.lab5.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unitbv.lab.lab5.persistence.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
