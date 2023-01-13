package ru.flobsterable.flashCards.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class WordsDeckEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val guid: String,
    val name: String,
    val transcription: String,
    @ColumnInfo(name = "img_path")
    val imgPath: String,
    @ColumnInfo(name = "word_sound")
    val wordSoundPath: String,
    @ColumnInfo(name = "meaning_sound")
    val meaningSoundPath: String,
    @ColumnInfo(name = "example_sound")
    val exampleSoundPath: String,
    @ColumnInfo(name = "meaning_text")
    val meaningText: String,
    @ColumnInfo(name = "example_text")
    val exampleText: String,
    val deckId: Int = 0,
)
