package ru.grobikon.springjdbcrestkotlin.service

import ru.grobikon.springjdbcrestkotlin.dto.CarDto

interface CarService {
    fun getAll(pageIndex: Int): List<CarDto>
    fun getById(id: Int): CarDto
    fun create(dto: CarDto): Int
    fun update(id: Int, dto: CarDto)
    fun delete(id: Int)
}