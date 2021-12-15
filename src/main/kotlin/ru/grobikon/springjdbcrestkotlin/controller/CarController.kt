package ru.grobikon.springjdbcrestkotlin.controller

import org.springframework.web.bind.annotation.*
import ru.grobikon.springjdbcrestkotlin.dto.CarDto
import ru.grobikon.springjdbcrestkotlin.service.CarService

@RestController
@RequestMapping("/cars")
class CarController(
    private val carService: CarService
){

    @GetMapping
    fun getAll(): List<CarDto> {
        return carService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): CarDto {
        return carService.getById(id = 1)
    }

    @PostMapping
    fun create(@RequestBody dto: CarDto): Int = carService.create(dto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody dto: CarDto) {
        carService.update(id, dto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {
        carService.delete(id)
    }
}