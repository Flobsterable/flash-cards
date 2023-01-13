package ru.flobsterable.flashCards.presentation.models

data class WordDataUi(
    val word: String,
    val transcription: String,
    val meaningText: String,
    val exampleText: String,
    val imagePath: String = "",
    val wordSoundPath: String = "",
    val meaningSoundPath: String = "",
    val exampleSoundPath: String = "",
)
