package com.microservices.repo

import com.microservices.data.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@RepositoryRestResource(collectionResourceRel = "address", path = "address")
interface AddressRepo: JpaRepository<Address, Long>