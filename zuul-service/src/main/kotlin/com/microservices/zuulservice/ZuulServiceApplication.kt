package com.microservices.zuulservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ZuulServiceApplication

fun main(args: Array<String>) {
	runApplication<ZuulServiceApplication>(*args)
}
