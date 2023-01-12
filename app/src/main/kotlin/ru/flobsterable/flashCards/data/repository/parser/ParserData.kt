package ru.flobsterable.flashCards.data.repository.parser

import ru.flobsterable.flashCards.data.database.entity.WordsDeckEntity
import ru.flobsterable.flashCards.data.repository.parser.models.Children
import ru.flobsterable.flashCards.data.repository.parser.models.Note
import kotlin.random.Random

interface ParserData {

    fun List<Children>.parserWordDataList(): List<WordsDeckEntity> {
        return this.map { it.notes.parseNoteList() }.flatten()
    }

    fun List<Note>.parseNoteList(): List<WordsDeckEntity> {
        return this.map { it.parserWordData() }
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
            exampleText = fieldList[6]
        )
    }

    fun String.preparation(): String {
        if (startsWith("<img src=\"")) return removeSurrounding("<img src=\"", "\" />")
        return if (startsWith("[sound:")) {
            removeSurrounding("[sound:", "]")
        } else {
            replace("<i>", "").replace("</i>", "").replace("<b>", "").replace("</b>", "")
        }
    }
}
