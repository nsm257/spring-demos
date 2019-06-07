package com.microservices.repo

import com.microservices.data.PhoneNumber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PhoneNumberRepo: JpaRepository<PhoneNumber, Long>