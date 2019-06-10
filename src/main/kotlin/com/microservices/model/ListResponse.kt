package com.microservices.model

internal class ListResponse<T>(val items: List<T>) {

    companion object {

        fun <T> of(items: List<T>): ListResponse<T> {
            return ListResponse(items)
        }
    }
}