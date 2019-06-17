//package com.microservices.service
////
////import com.microservices.resource.FilterResource
////import com.sun.deploy.util.ReflectionUtil.getClass
////import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature
////import org.glassfish.jersey.server.ResourceConfig
////import org.springframework.context.annotation.Configuration
////import org.springframework.stereotype.Component
////import javax.ws.rs.ApplicationPath
////
////@Configuration
////@ApplicationPath("/api")
//////@Configuration
////class JerseyConfig: ResourceConfig() {
////
////    fun JerseyConfig() {
//////        packages(getClass().getPackage().getName() + ".resources")
//////        packages("com.microservices")
//////
////        packages("com.microservices.resource")
//////        register(SelectableEntityFilteringFeature::class.java)
//////        property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, "select")
////    }
////
////}