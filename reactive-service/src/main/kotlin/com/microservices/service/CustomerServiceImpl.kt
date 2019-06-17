package com.microservices.service

import com.microservices.data.Customer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl: CustomerService {

    companion object {
        private val log = LoggerFactory.getLogger(CustomerServiceImpl::class.java)

        val initialCustomers = arrayOf(Customer(1, "Kotlin"),
                Customer(2, "Spring"),
                Customer(3, "Microservice", Customer.Telephone("+44", "7123456789")))

    }

    val customers = ConcurrentHashMap<Int, Customer> (initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int) = Customer(id).toMono()

    override fun searchCustomers(nameFilter: String): Flux<Customer> =
            customers.filter {
                it.value.name.contains(nameFilter, true)
            }.map(Map.Entry<Int, Customer>::value).toFlux()

    override fun createCustomer(customerMono: Mono<Customer>): Mono<*> {
        return customerMono.subscribe {
            customers[it.id] = it
        }.toMono()
    }
}