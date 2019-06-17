package com.microservices.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.bohnman.squiggly.web.SquigglyRequestFilter
import com.github.bohnman.squiggly.context.provider.SquigglyFilterHolder.setFilter
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider
import com.github.bohnman.squiggly.Squiggly
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


//@Configuration
//@ConditionalOnClass(ObjectMapper::class)
//class SquigglyAutoconfigure {
//
//    @Bean
//    fun squigglyRequestFilter(objectMapper: ObjectMapper): Any {
//        Squiggly.init(objectMapper, RequestSquigglyContextProvider())
//
//        val filter = FilterRegistrationBean<SquigglyRequestFilter>()
//        filter.filter = SquigglyRequestFilter()
//        filter.order = 1
//
//        return filter
//    }
//}