package ru.flobsterable.flashCards.data.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "deck_table",
    indices = [Index(value = ["name"], unique = true)]
)
data class DecksEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)
