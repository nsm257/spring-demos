package com.microservices.repo

import com.microservices.data.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@RepositoryRestResource(collectionResourceRel = "person", path = "person")
interface PersonRepo: JpaRepository<Person, Long>