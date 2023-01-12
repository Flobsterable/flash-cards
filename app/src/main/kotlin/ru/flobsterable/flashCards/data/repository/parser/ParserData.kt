package ru.flobsterable.flashCards.data.repository.parser

import ru.flobsterable.flashCards.data.database.entity.DecksEntity
import ru.flobsterable.flashCards.data.database.entity.WordsDeckEntity
import ru.flobsterable.flashCards.data.repository.parser.models.Children
import ru.flobsterable.flashCards.data.repository.parser.models.Deck
import ru.flobsterable.flashCards.data.repository.parser.models.Note
import ru.flobsterable.flashCards.presentation.screens.models.DeckDataUi
import ru.flobsterable.flashCards.presentation.screens.models.WordDataUi
import kotlin.random.Random

interface ParserData {
    fun Deck.parser(): DecksEntity {
        return DecksEntity(
            name = this.name,
        )
    }

    fun List<Children>.parserWordDataList(mediaDir: String, deckId: Int): List<WordsDeckEntity> {
        return this.dropLast(this.lastIndex).map { it.notes.parseNoteList(mediaDir, deckId) }.flatten()
    }

    fun List<Note>.parseNoteList(mediaDir: String, deckId: Int): List<WordsDeckEntity> {
        return this.map { it.parserWordData().copy(deckId = deckId) }
    }

    fun Note.parserWordData(): WordsDeckEntity {
        val fieldList = this.fields.map { it.preparation() }

        return WordsDeckEntity(
            id = Random.nextInt(),
            guid = this.guid,
            name = fieldList[0],
            transcription = fieldList[7],
            imgPath = fieldList[1],
            wordSoundPath = fieldList[2],
            meaningSoundPath = fieldList[3],
            exampleSoundPath = fieldList[4],
            meaningText = fieldList[5],
            exampleText = fieldList[6],
        )
    }

    fun List<DecksEntity>.toDecksUiData(): List<DeckDataUi> {
        return this.map { it.toUiData() }
    }

    fun DecksEntity.toUiData(): DeckDataUi {
        return DeckDataUi(
            this.id,
            this.name
        )
    }

    fun List<WordsDeckEntity>.toWordsUiData(): List<WordDataUi> {
        return this.map { it.toUiData() }
    }

    fun WordsDeckEntity.toUiData(): WordDataUi {
        return WordDataUi(
            word = this.name,
            transcription = this.transcription,
            meaningText = this.meaningText,
            exampleText = this.exampleText,
            imagePath = this.imgPath,
            wordSoundPath = this.wordSoundPath,
            meaningSoundPath = this.meaningSoundPath,
            exampleSoundPath = this.exampleSoundPath
        )
    }

    private fun String.preparation(): String {
        if (startsWith("<img src=\"")) return removeSurrounding("<img src=\"", "\" />")
        return if (startsWith("[sound:")) {
            removeSurrounding("[sound:", "]")
        } else {
            replace("<i>", "")
                .replace("</i>", "")
                .replace("<b>", "")
                .replace("</b>", "")
        }
    }
}
