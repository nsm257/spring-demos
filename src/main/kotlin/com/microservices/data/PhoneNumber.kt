package com.microservices.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ht_phone_number")
data class PhoneNumber (
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

        @Column(name = "region")
        var region: String?,

        @Column(name = "contact")
        var contact: String?
)