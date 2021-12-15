package ru.grobikon.springjdbcrestkotlin.exception

import org.springframework.http.HttpStatus

/**
 * Базовое исключение которое будем использовать во всем приложении
 */
abstract class BaseException(
    val status: HttpStatus,
    val errorCode: String,
    override val message: String): RuntimeException(message)