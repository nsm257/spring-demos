package com.microservices.service

import com.microservices.data.Address
import com.microservices.data.Person
import com.microservices.data.PhoneNumber
import com.microservices.repo.AddressRepo
import com.microservices.repo.PersonRepo
import com.microservices.repo.PhoneNumberRepo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FilterService {

    companion object {
        private val log = LoggerFactory.getLogger(FilterService::class.java)
    }

    @Autowired
    private lateinit var addressRepo: AddressRepo

    @Autowired
    private lateinit var personRepo: PersonRepo

    @Autowired
    private lateinit var phoneNumberRepo: PhoneNumberRepo

    fun save(person: Person): Person {
        log.info("Inside save $person")

        val ph = phoneNumberRepo.save(PhoneNumber(0, LocalDateTime.now(), LocalDateTime.now(), "ab", "ab",
                person.address.phNumber!!.region, person.address.phNumber!!.contact))

        person.address.phNumberId = ph.id
        person.address.createdOn = LocalDateTime.now()
        person.address.updatedOn = LocalDateTime.now()
        person.address.createdBy = "ab"
        person.address.updatedBy = "ab"

        val add = addressRepo.save(person.address)
        person.address_id = add.id

        person.createdBy = "ab"
        person.updatedBy = "ab"
        
        return personRepo.save(person)
    }

    fun get(id: Long): Person {
        log.info("Inside get")

        val perOpt = personRepo.findById(id)
        if (!perOpt.isPresent) throw Exception("hello world")

        val per = perOpt.get()
        per.address = addressRepo.getOne(per.address_id)

        per.address.phNumber = phoneNumberRepo.getOne(per.address.phNumberId!!)
        return per
    }
}