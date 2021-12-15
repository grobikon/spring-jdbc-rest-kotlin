package ru.grobikon.springjdbcrestkotlin.model

/**
 * Объект, который будет храниться в БД
 */
data class Car(
    val id: Int = 0,
    val title: String,
    val passengerCount: Int? = null,     //Количество пассажиров
)