package ru.flobsterable.flashCards.data.repository.parser.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Deck(
    @Json(name = "children")
    val children: List<Children>,
    @Json(name = "crowdanki_uuid")
    val crowdankiUuid: String,
    @Json(name = "deck_config_uuid")
    val deckConfigUuid: String,
    @Json(name = "deck_configurations")
    val deckConfigurations: List<DeckConfiguration>,
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
    @Json(name = "note_models")
    val noteModels: List<NoteModel>,
    @Json(name = "notes")
    val notes: List<Any>,
    @Json(name = "__type__")
    val type: String
)
