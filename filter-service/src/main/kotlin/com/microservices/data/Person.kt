package com.microservices.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*
import javax.xml.bind.annotation.XmlRootElement

@Entity
@Table(name = "person")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
data class Person (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @Column(name = "created_on")
        var createdOn: LocalDateTime?,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @Column(name = "updated_on")
        var updatedOn: LocalDateTime?,

        @Column(name = "created_by")
        var createdBy: String?,

        @Column(name = "updated_by")
        var updatedBy: String?,

        @Column(name = "email_id")
        var email: String,

        @Column(name = "name")
        var name: String,

        @Column(name = "address_id")
        var address_id: Long,

        @Transient
        var address: Address
)