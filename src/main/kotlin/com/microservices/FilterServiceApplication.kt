package com.microservices

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider
import com.github.bohnman.squiggly.Squiggly
import com.github.bohnman.squiggly.web.SquigglyRequestFilter
import com.google.common.collect.Iterables
import com.microservices.model.ListResponse
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import javax.servlet.http.HttpServletRequest

@SpringBootApplication
class FilterServiceApplication {

	@Bean
	fun squigglyRequestFilter(): FilterRegistrationBean<SquigglyRequestFilter> {
		val filter = FilterRegistrationBean<SquigglyRequestFilter>()
		filter.filter = SquigglyRequestFilter()
		filter.order = 1
		return filter
	}
}

fun main(args: Array<String>) {
	val context = runApplication<FilterServiceApplication>(*args)

	val objectMappers = context.getBeansOfType(ObjectMapper::class.java).values

	Squiggly.init(objectMappers, object : RequestSquigglyContextProvider() {
		override fun customizeFilter(filter: String?, request: HttpServletRequest, beanClass: Class<*>): String? {
			var filter = filter

			// OPTIONAL: automatically wrap filter expressions in items{} when the object is a ListResponse
			if (filter != null && ListResponse::class.java!!.isAssignableFrom(beanClass)) {
				filter = "items[$filter]"
			}

			return filter
		}
	})

	val objectMapper = Iterables.getFirst(objectMappers, null)

	// Enable Squiggly for Jackson message converter
	if (objectMapper != null) {
		for (converter in context.getBeansOfType(MappingJackson2HttpMessageConverter::class.java).values) {
			converter.objectMapper = objectMapper
		}
	}
}
