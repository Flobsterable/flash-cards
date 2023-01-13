package ru.flobsterable.flashCards.data.repository.parser.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Children(
    @Json(name = "children")
    val children: List<Any>,
    @Json(name = "crowdanki_uuid")
    val crowdankiUuid: String,
    @Json(name = "deck_config_uuid")
    val deckConfigUuid: String,
    @Json(name = "desc")
    val desc: String,
    @Json(name = "dyn")
    val dyn: Int,
    @Json(name = "extendNew")
    val extendNew: Int,
    @Json(name = "extendRev")
    val extendRev: Int,
    @Json(name = "media_files")
    val mediaFiles: List<String>,
    @Json(name = "name")
    val name: String,
    @Json(name = "notes")
    val notes: List<Note>,
    @Json(name = "__type__")
    val type: String
)
