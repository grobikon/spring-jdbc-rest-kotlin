package ru.grobikon.springjdbcrestkotlin.repository

import ru.grobikon.springjdbcrestkotlin.model.Car

interface CarRepository {
    fun getAll(pageIndex: Int): List<Car>
    fun findById(id: Int): Car?
    fun create(title: String, passengerCount: Int?): Int
    fun update(id: Int, title: String, passengerCount: Int?)
    fun delete(id: Int)
}