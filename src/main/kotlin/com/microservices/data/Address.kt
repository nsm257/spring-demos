package com.microservices.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "ht_address")
data class Address (
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

        @Column(name = "pincode")
        var pincode: String?,

        @Column(name = "city")
        var city: String?,

        @Column(name = "state")
        var state: String?,

        @Column(name = "ph_number_id")
        var phNumberId: Long?,

        @Transient
        var phNumber: PhoneNumber?
)