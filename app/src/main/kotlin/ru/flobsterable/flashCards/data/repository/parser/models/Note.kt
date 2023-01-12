package ru.flobsterable.flashCards.data.repository.parser.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Note(
    @Json(name = "fields")
    val fields: List<String>,
    @Json(name = "guid")
    val guid: String,
    @Json(name = "note_model_uuid")
    val noteModelUuid: String,
    @Json(name = "tags")
    val tags: List<Any>,
    @Json(name = "__type__")
    val type: String
)
