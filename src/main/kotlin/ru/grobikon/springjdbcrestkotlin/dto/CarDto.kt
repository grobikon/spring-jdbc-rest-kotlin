package ru.grobikon.springjdbcrestkotlin.dto

/**
 * Объект, который будет нужен для отправки на frontend
 */
data class CarDto(
    val id: Int = 0,
    val title: String,
    val passengerCount: Int? = null     //Количество пассажиров
)
