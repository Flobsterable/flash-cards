package ru.flobsterable.flashCards.data.repository.parser.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Fld(
    @Json(name = "description")
    val description: String,
    @Json(name = "font")
    val font: String,
    @Json(name = "media")
    val media: List<Any>,
    @Json(name = "name")
    val name: String,
    @Json(name = "ord")
    val ord: Int,
    @Json(name = "rtl")
    val rtl: Boolean,
    @Json(name = "size")
    val size: Int,
    @Json(name = "sticky")
    val sticky: Boolean
)
