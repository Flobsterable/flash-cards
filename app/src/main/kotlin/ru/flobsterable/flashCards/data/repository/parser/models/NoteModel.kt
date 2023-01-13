package ru.flobsterable.flashCards.data.repository.parser.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NoteModel(
    @Json(name = "crowdanki_uuid")
    val crowdankiUuid: String,
    @Json(name = "css")
    val css: String,
    @Json(name = "flds")
    val flds: List<Fld>,
    @Json(name = "latexPost")
    val latexPost: String,
    @Json(name = "latexPre")
    val latexPre: String,
    @Json(name = "latexsvg")
    val latexsvg: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "req")
    val req: List<List<Any>>,
    @Json(name = "sortf")
    val sortf: Int,
    @Json(name = "tags")
    val tags: List<Any>,
    @Json(name = "tmpls")
    val tmpls: List<Tmpl>,
    @Json(name = "__type__")
    val type: String,
    @Json(name = "type")
    val typeInt: Int,
    @Json(name = "vers")
    val vers: List<Any>
)
