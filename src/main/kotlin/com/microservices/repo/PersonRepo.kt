package com.microservices.repo

import com.microservices.data.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepo: JpaRepository<Person, Long>