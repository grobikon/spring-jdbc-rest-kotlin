package ru.grobikon.springjdbcrestkotlin.exception

import org.springframework.http.HttpStatus

class CarNotFoundException(id: Int): BaseException(
    status = HttpStatus.NOT_FOUND,
    errorCode = "Not found car",
    message = "Car with id = $id not found"
)