package com.otus.homework.service

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import javax.sql.DataSource

@Repository
class UserDao(dataSource: DataSource) {
    private val jdbcTemplate = JdbcTemplate(dataSource)

    fun getAll(): String {
        return jdbcTemplate.queryForObject(
            "select name from client",
            String::class.java
        ).toString()
    }
}