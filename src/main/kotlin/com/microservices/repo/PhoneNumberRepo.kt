package com.microservices.repo

import com.microservices.data.PhoneNumber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@RepositoryRestResource(collectionResourceRel = "phoneNumber", path = "phoneNumber")
interface PhoneNumberRepo: JpaRepository<PhoneNumber, Long>