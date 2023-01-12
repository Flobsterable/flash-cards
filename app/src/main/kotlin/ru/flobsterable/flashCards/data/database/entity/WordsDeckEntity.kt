package ru.flobsterable.flashCards.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "WordsDeck")
class WordsDeckEntity(
    val id: Int,
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
)
