package com.otus.homework.controller

import com.otus.homework.dao.ClientDao
import com.otus.homework.dto.Client
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Controller(val clientDao: ClientDao) {

    @GetMapping("/health")
    fun health(): ResponseEntity<String> {
        return ResponseEntity("OK", HttpStatus.OK)
    }

    @GetMapping("/")
    fun default(): ResponseEntity<String> {
        val env = System.getenv("HOSTNAME")
        return ResponseEntity("Hello from $env", HttpStatus.OK)
    }

    //    @RequestMapping("/client")
    @RequestMapping(value = ["/client"], params = ["id"], method = [RequestMethod.GET])
    fun getById(@RequestParam id: Long): ResponseEntity<Client> {
        return ResponseEntity(clientDao.getById(id), HttpStatus.OK)
    }

    @RequestMapping(value = ["/client"], params = ["firstname", "lastname"], method = [RequestMethod.GET])
//    @RequestMapping("/client")
    fun getByFirstAndLastname(
        @RequestParam firstname: String,
        @RequestParam lastname: String
    ): ResponseEntity<List<Client>> {
        return ResponseEntity(clientDao.getByFirstAndLastname(firstname, lastname), HttpStatus.OK)
    }

    @RequestMapping("/client/all")
    fun getAll(): ResponseEntity<List<Client>> {
        return ResponseEntity(clientDao.getAll(), HttpStatus.OK)
    }

    @PostMapping("/client")
    fun createClient(@RequestBody client: Client): ResponseEntity<Void> {
        clientDao.createClient(client)
        return ResponseEntity(HttpStatus.OK)
    }
}
