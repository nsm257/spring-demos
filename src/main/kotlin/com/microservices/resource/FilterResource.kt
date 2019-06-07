package com.microservices.resource

import com.microservices.data.Person
import com.microservices.service.FilterService
//import javax.ws.rs.core.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
//import org.springframework.stereotype.Component
//import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
//import javax.ws.rs.*
//import javax.ws.rs.Path
//import javax.ws.rs.Produces
//import javax.xml.bind.annotation.XmlAccessType
//import javax.xml.bind.annotation.XmlAccessorType
//import javax.xml.bind.annotation.XmlRootElement

//@Path("/person")
//@Component
//@Service
//@XmlAccessorType(XmlAccessType.NONE)
//@XmlRootElement
@RestController
@RequestMapping("/person")
class FilterResource {

    @Autowired
    private lateinit var filterService: FilterService

//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable ("id") id: Long) = filterService.get(id)

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody person: Person) = filterService.save(person)
}