package ru.grobikon.springjdbcrestkotlin.dto

data class ApiError(
    val errorCode: String,
    val message: String
)