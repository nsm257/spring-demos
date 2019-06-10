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
import jdk.nashorn.internal.objects.NativeArray.forEach
import com.github.bohnman.squiggly.context.provider.SquigglyContextProvider
import com.microservices.data.Address
import com.microservices.data.Person
import com.microservices.data.PhoneNumber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter
import javax.persistence.EntityManager
import javax.persistence.metamodel.EntityType
import javax.persistence.metamodel.Type





@SpringBootApplication
class HateoasServiceApplication {

	@Bean
	fun squigglyRequestFilter(): FilterRegistrationBean<SquigglyRequestFilter> {
		val filter = FilterRegistrationBean<SquigglyRequestFilter>()
		filter.filter = SquigglyRequestFilter()
		filter.order = 1
		return filter
	}

	@Configuration
	inner class RestConfig
	constructor (private val entityManager: EntityManager) : RepositoryRestConfigurer {

		override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
			config.exposeIdsFor(Person::class.java, Address::class.java, PhoneNumber::class.java)
		}
	}

//	@Configuration
//	inner class RestConfig @Autowired
//	constructor(private val entityManager: EntityManager) : RepositoryRestConfigurer {
//
//		override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
//			config.exposeIdsFor(
//					entityManager.metamodel.entities.stream()
//							.map(Function<EntityType<*>, Any> { Type.getJavaType() })
//							.toArray(Class[]::new  /* Currently unsupported in Kotlin */))
//		}
//	}
}


fun main(args: Array<String>) {
	val context = runApplication<HateoasServiceApplication>(*args)

	val contextProvider = RequestSquigglyContextProvider()

	context.getBeansOfType(MappingJackson2HttpMessageConverter::class.java)
			.values
			.stream()
			.map(MappingJackson2HttpMessageConverter::getObjectMapper)
	.forEach({ objectMapper -> Squiggly.init(objectMapper, contextProvider) })

//	val objectMappers = context.getBeansOfType(ObjectMapper::class.java).values
//
//	Squiggly.init(objectMappers, object : RequestSquigglyContextProvider() {
//		override fun customizeFilter(filter: String?, request: HttpServletRequest, beanClass: Class<*>): String? {
//			var filter = filter
//
//			// OPTIONAL: automatically wrap filter expressions in items{} when the object is a ListResponse
//			if (filter != null && ListResponse::class.java!!.isAssignableFrom(beanClass)) {
//				filter = "items[$filter]"
//			}
//
//			return filter
//		}
//	})
//
//	val objectMapper = Iterables.getFirst(objectMappers, null)
//
//	// Enable Squiggly for Jackson message converter
//	if (objectMapper != null) {
//		for (converter in context.getBeansOfType(MappingJackson2HttpMessageConverter::class.java).values) {
//			converter.objectMapper = objectMapper
//		}
//	}
}
