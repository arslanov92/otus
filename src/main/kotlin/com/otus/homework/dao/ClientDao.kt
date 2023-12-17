package com.otus.homework.dao

import com.otus.homework.dto.Client
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RequestParam
import java.sql.ResultSet
import javax.sql.DataSource

@Repository
class ClientDao(dataSource: DataSource) {
    private val jdbcTemplate = JdbcTemplate(dataSource)

    fun getAll(): List<Client> {
        return jdbcTemplate.query(
            "select * from client"
        ) { rs, _ -> rs.client }
    }

    fun createClient(client: Client) {
        jdbcTemplate.update(
            "insert into client(firstname, lastname) values (?, ?)",
            client.firstname,
            client.lastname
        )
    }

    fun getById(id: Long): Client? {
        return jdbcTemplate.query(
            "select * from client where id = ?",
            { rs, _ -> rs.client },
            id
        ).firstOrNull()
    }

    fun getByFirstAndLastname(@RequestParam firstname: String, @RequestParam lastname: String): List<Client> {
        return jdbcTemplate.query(
            "select * from client where firstname = ? and lastname=?",
            { rs, _ -> rs.client },
            firstname,
            lastname
        )
    }

    private val ResultSet.client: Client
        get() = Client(
            getLong("id"),
            getString("firstname"),
            getString("lastname")
        )
}