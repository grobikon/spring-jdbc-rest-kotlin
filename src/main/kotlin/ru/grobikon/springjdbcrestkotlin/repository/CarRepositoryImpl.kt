package ru.grobikon.springjdbcrestkotlin.repository

import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import ru.grobikon.springjdbcrestkotlin.model.Car
import ru.grobikon.springjdbcrestkotlin.util.getIntOrNull
import java.sql.ResultSet

/**
 * NamedParameterJdbcTemplate основная сущность для работы с БД, позволяет использовать именованные параметры в запросах.
 *                            Позволяет писать нативные запросы к БД
 */
@Repository
class CarRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : CarRepository {
    override fun getAll(pageIndex: Int): List<Car> =
        jdbcTemplate.query(
            "select * from car order by id limit :limit offset :offset",
            mapOf(
                "limit" to PAGE_SIZE,
                "offset" to PAGE_SIZE * pageIndex
            ),
            ROW_MAPPER
        )

    /**
     * Чтение данных делаем через query
     */
    override fun findById(id: Int): Car? =
        jdbcTemplate.query(
            "select * from car where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).firstOrNull()

    /**
     * Все манипуляции с БД делаем через update
     * keyHolder - получает id созданного объекта
     */
    override fun create(title: String, passengerCount: Int?): Int {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "insert into car (title, passenger_count) values (:title, :passengerCount)",
            MapSqlParameterSource(mapOf(
                "title" to title,
                "passengerCount" to passengerCount,
            )),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return keyHolder.keys?.getValue("id") as Int
    }

    override fun update(id: Int, title: String, passengerCount: Int?) {
        jdbcTemplate.update(
            "update car set title = :title, passenger_count = :passengerCount where id = :id",
            mapOf(
                "id" to id,
                "title" to title,
                "passengerCount" to passengerCount,
            )
        )
    }

    override fun delete(id: Int) {
        jdbcTemplate.update(
            "delete from car where id = :id",
            mapOf(
                "id" to id,
            )
        )
    }

    override fun getCarStatistics(): Map<String, Int> =
        jdbcTemplate.query(
            """select cb.title, count(c.id) 
                    from car c join car_brand cb on c.brand_id = cb.id
                    group by cb.title
                """,
            EXTRACTOR
        )!!

    /**
     * Мапит поля нашей БД в поля нашей сущности
     * rs - построчно считывает данные
     */
    private companion object{
        const val PAGE_SIZE = 3
        val ROW_MAPPER = RowMapper<Car> { rs: ResultSet, _ ->
            Car(
                id = rs.getInt("id"),
                title = rs.getString("title"),
                passengerCount = rs.getIntOrNull("passenger_count"),
            )
        }

        val EXTRACTOR = ResultSetExtractor<Map<String, Int>> { rs ->
            val result = mutableMapOf<String, Int>()
            while (rs.next()) {
                val title = rs.getString("title")
                result.getOrPut(title) { 0 }
                result[title] = result.getValue(title) + rs.getInt("count")
            }
            result
        }
    }
}