package com.microservices.repo

import com.microservices.data.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepo: JpaRepository<Address, Long>