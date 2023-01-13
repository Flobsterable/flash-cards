package ru.flobsterable.flashCards.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class DeckWithWords(
    @Embedded val deck: DecksEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "deckId"
    )
    val words: List<WordsDeckEntity>
)
