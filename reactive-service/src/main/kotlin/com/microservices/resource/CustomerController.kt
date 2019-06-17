package com.microservices.resource

import com.microservices.data.Customer
import com.microservices.service.CustomerService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class CustomerController {

    companion object {
        private val log = LoggerFactory.getLogger(CustomerController::class.java)
    }

    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Mono<Customer>> {

        val customer = customerService.getCustomer(id)
        return ResponseEntity.ok(customer)
    }

    @GetMapping("/customers")
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            customerService.searchCustomers(nameFilter)

    @PostMapping("/customer/")
    fun createCustomer(@RequestBody customerMono: Mono<Customer>) =
            ResponseEntity.ok(customerService.createCustomer(customerMono))
}