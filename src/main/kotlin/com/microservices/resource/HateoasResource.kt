package com.microservices.resource

import com.microservices.data.Person
import com.microservices.service.HateoasService
//import javax.ws.rs.core.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
//import org.springframework.stereotype.Component
//import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

//@RestController
//@RequestMapping("/person")
//class HateoasResource {
//
//    @Autowired
//    private lateinit var hateoasService: HateoasService
//
//    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
//    fun get(@PathVariable ("id") id: Long) = hateoasService.get(id)
//
//    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
//    fun save(@RequestBody person: Person) = hateoasService.save(person)
//}