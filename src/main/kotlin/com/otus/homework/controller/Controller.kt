package com.otus.homework.controller

import com.otus.homework.service.UserDao
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(val userDao: UserDao) {

    @GetMapping("/health")
    fun health(): Response {
        return Response("OK")
    }

    @GetMapping("/")
    fun default(): Response {
        val env = System.getenv("HOSTNAME")
        return Response("Hello from $env")
    }

    @GetMapping("/user")
    fun getName(): Response {
        return Response(userDao.getAll())
    }
}

data class Response(val status: String)