package ru.flobsterable.flashCards.data.repository.parser.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeckConfiguration(
    @Json(name = "autoplay")
    val autoplay: Boolean,
    @Json(name = "buryInterdayLearning")
    val buryInterdayLearning: Boolean,
    @Json(name = "crowdanki_uuid")
    val crowdankiUuid: String,
    @Json(name = "dyn")
    val dyn: Boolean,
    @Json(name = "interdayLearningMix")
    val interdayLearningMix: Int,
    @Json(name = "lapse")
    val lapse: Lapse,
    @Json(name = "maxTaken")
    val maxTaken: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "new")
    val new: New,
    @Json(name = "newGatherPriority")
    val newGatherPriority: Int,
    @Json(name = "newMix")
    val newMix: Int,
    @Json(name = "newPerDayMinimum")
    val newPerDayMinimum: Int,
    @Json(name = "newSortOrder")
    val newSortOrder: Int,
    @Json(name = "replayq")
    val replayq: Boolean,
    @Json(name = "rev")
    val rev: Rev,
    @Json(name = "reviewOrder")
    val reviewOrder: Int,
    @Json(name = "timer")
    val timer: Int,
    @Json(name = "__type__")
    val type: String
)
