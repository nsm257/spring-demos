package com.microservices

//import org.glassfish.jersey.server.spring.scope.RequestContextFilter
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider
import com.github.bohnman.squiggly.Squiggly
import com.github.bohnman.squiggly.web.SquigglyRequestFilter
import com.github.bohnman.squiggly.context.provider.SquigglyFilterHolder.setFilter
import com.google.common.collect.Iterables
import com.microservices.model.ListResponse
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import javax.servlet.http.HttpServletRequest


//import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature
//import org.glassfish.jersey.server.ResourceConfig
//import org.hibernate.criterion.Projections.property
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
//import org.springframework.context.annotation.ComponentScan
//import org.springframework.boot.SpringApplication
//import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter
//import org.springframework.context.annotation.Bean


@SpringBootApplication
class FilterServiceApplication {

	@Bean
	fun squigglyRequestFilter(): FilterRegistrationBean<SquigglyRequestFilter> {
		val filter = FilterRegistrationBean<SquigglyRequestFilter>()
		filter.filter = SquigglyRequestFilter()
		filter.order = 1
		return filter
	}

//	@Bean
//	fun requestContextFilter(): RequestContextFilter {
//		val filter = OrderedRequestContextFilter()
//		filter.order = -100001
//		return RequestContextFilter(filter)
//	}
}
//: SpringBootServletInitializer() {
//	init {
//		// Register all resources present under the package.
//		packages("org.glassfish.jersey.examples.entityfiltering.selectable")
//
//		// Register entity-filtering selectable feature.
//		register(SelectableEntityFilteringFeature::class.java)
//		property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, "select")
//
//		// Configure MOXy Json provider. Comment this line to use Jackson. Uncomment to use MOXy.
//		register(MoxyJsonConfig().setFormattedOutput(true).resolver())
//
//		// Configure Jackson Json provider. Comment this line to use MOXy. Uncomment to use Jackson.
//		// register(JacksonFeature.class);
//	}
//}


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
