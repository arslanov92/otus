package com.otus.homework.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @GetMapping("/health")
    fun health(): Response {
        return Response("OK")
    }

    @GetMapping("/")
    fun default(): Response {
        val env = System.getenv("HOSTNAME")
        return Response("Hello from $env")
    }
}

data class Response(val status: String)