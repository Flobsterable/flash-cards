package ru.flobsterable.flashCards.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "decks_table")
class DecksEntity(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "cards_id")
    val cardsId: String,
)
