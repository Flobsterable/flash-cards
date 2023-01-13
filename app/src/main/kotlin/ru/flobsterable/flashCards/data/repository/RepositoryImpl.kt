package ru.flobsterable.flashCards.data.repository

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import ru.flobsterable.flashCards.data.database.DatabaseDao
import ru.flobsterable.flashCards.data.models.Resource
import ru.flobsterable.flashCards.data.repository.parser.ParserData
import ru.flobsterable.flashCards.data.repository.parser.models.Deck
import ru.flobsterable.flashCards.data.utils.findFileByExtension
import ru.flobsterable.flashCards.presentation.models.DeckDataUi
import ru.flobsterable.flashCards.presentation.models.WordDataUi
import java.io.File
import javax.inject.Inject

private const val JSON_EXTENSION = "json"
private const val AUDIO_EXTENSION = "mp3"

class RepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: DatabaseDao,
) : Repository, ParserData {

    override suspend fun getDeckList(): Flow<Resource<List<DeckDataUi>>> = flow {
        val data = database.getDeckList().first()
        when (data.isNotEmpty()) {
            true -> emit(Resource.Success(data.toDecksUiData()))
            false -> emit(Resource.Error("isEmpty"))
        }
    }

    override suspend fun wordsListFromDeck(deckId: Int): Flow<Resource<List<WordDataUi>>> = flow {
        val data = database.getDeckWithWords(deckId).firstOrNull()
        when (data != null) {
            true -> emit(Resource.Success(data.words.toWordsUiData()))
            false -> emit(Resource.Error("isEmpty"))
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun saveIntoDatabase(direction: String) {
        val jsonFile = findFileByExtension(File(context.filesDir, direction), JSON_EXTENSION)
        val json = jsonFile?.inputStream()?.bufferedReader().use { it!!.readText() }
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Deck>()
        val deck = jsonAdapter.fromJson(json)
        val mediaDir = findFileByExtension(
            File(context.filesDir, direction),
            AUDIO_EXTENSION
        )?.parent + "/"

        val deckId = database.insertDeck(deck!!.parser())
        database.insertWords(deck.children.parserWordDataList(mediaDir, deckId.toInt()))
    }
}
