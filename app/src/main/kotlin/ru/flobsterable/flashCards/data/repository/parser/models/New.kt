package ru.flobsterable.flashCards.data.repository.parser.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class New(
    @Json(name = "bury")
    val bury: Boolean,
    @Json(name = "delays")
    val delays: List<Double>,
    @Json(name = "initialFactor")
    val initialFactor: Int,
    @Json(name = "ints")
    val ints: List<Int>,
    @Json(name = "order")
    val order: Int,
    @Json(name = "perDay")
    val perDay: Int
)
