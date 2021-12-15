package ru.grobikon.springjdbcrestkotlin.service

import org.springframework.stereotype.Service
import ru.grobikon.springjdbcrestkotlin.dto.CarDto
import ru.grobikon.springjdbcrestkotlin.model.Car
import ru.grobikon.springjdbcrestkotlin.repository.CarRepository

@Service
class CarServiceImpl(
    private val carRepository: CarRepository
) : CarService {

    override fun getAll(): List<CarDto> = carRepository.getAll()
        .map { it.toDto() }

    override fun getById(id: Int): CarDto =
        carRepository.findById(id)
            ?.toDto()
            ?: throw RuntimeException("Cargo with id =$id")

    override fun create(dto: CarDto): Int =
        carRepository.create(dto.title, dto.passengerCount)


    override fun update(id: Int, dto: CarDto) {
        carRepository.update(id, dto.title, dto.passengerCount)
    }

    override fun delete(id: Int) {
        carRepository.delete(id)
    }

    private fun Car.toDto() = CarDto(
        id = id,
        title = title,
        passengerCount = passengerCount,
    )
}