package ru.flobsterable.flashCards.data.repository.parser.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tmpl(
    @Json(name = "afmt")
    val afmt: String,
    @Json(name = "bafmt")
    val bafmt: String,
    @Json(name = "bfont")
    val bfont: String,
    @Json(name = "bqfmt")
    val bqfmt: String,
    @Json(name = "bsize")
    val bsize: Int,
    @Json(name = "did")
    val did: Any? = null,
    @Json(name = "name")
    val name: String,
    @Json(name = "ord")
    val ord: Int,
    @Json(name = "qfmt")
    val qfmt: String
)
