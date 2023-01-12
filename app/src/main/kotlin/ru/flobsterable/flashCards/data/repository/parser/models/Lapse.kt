package ru.flobsterable.flashCards.data.repository.parser.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Lapse(
    @Json(name = "delays")
    val delays: List<Double>,
    @Json(name = "leechAction")
    val leechAction: Int,
    @Json(name = "leechFails")
    val leechFails: Int,
    @Json(name = "minInt")
    val minInt: Int,
    @Json(name = "mult")
    val mult: Double
)
