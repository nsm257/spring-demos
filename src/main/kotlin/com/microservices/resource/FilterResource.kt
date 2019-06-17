package com.microservices.resource

import com.microservices.data.Person
import com.microservices.service.FilterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class FilterResource {

    @Autowired
    private lateinit var filterService: FilterService

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable ("id") id: Long) = filterService.get(id)

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody person: Person) = filterService.save(person)
}