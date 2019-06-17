package com.microservices

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@EnableZuulProxy
@SpringBootApplication
class ZuulServiceApplication

fun main(args: Array<String>) {
	runApplication<ZuulServiceApplication>(*args)
}
