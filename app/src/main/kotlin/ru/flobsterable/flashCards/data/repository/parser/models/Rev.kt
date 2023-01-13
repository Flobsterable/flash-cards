package ru.flobsterable.flashCards.data.repository.parser.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rev(
    @Json(name = "bury")
    val bury: Boolean,
    @Json(name = "ease4")
    val ease4: Double,
    @Json(name = "hardFactor")
    val hardFactor: Double,
    @Json(name = "ivlFct")
    val ivlFct: Double,
    @Json(name = "maxIvl")
    val maxIvl: Int,
    @Json(name = "perDay")
    val perDay: Int
)
