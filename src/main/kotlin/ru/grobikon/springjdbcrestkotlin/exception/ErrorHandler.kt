package ru.grobikon.springjdbcrestkotlin.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.grobikon.springjdbcrestkotlin.dto.ApiError

@ControllerAdvice
class ErrorHandler: ResponseEntityExceptionHandler() {

    /**
     * Метод для обработки наших Custom Exception
     */
    @ExceptionHandler(BaseException::class)
    fun handlerBaseException(e: BaseException): ResponseEntity<ApiError> {
        val apiError = ApiError(
            errorCode = e.errorCode,
            message = e.message
        )
        return ResponseEntity(apiError, e.status)
    }
}