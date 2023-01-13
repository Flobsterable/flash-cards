package ru.flobsterable.flashCards.data.repository

import kotlinx.coroutines.flow.Flow
import ru.flobsterable.flashCards.data.models.Resource
import ru.flobsterable.flashCards.presentation.models.DeckDataUi
import ru.flobsterable.flashCards.presentation.models.WordDataUi

interface Repository {
    suspend fun saveIntoDatabase(direction: String)
    suspend fun getDeckList(): Flow<Resource<List<DeckDataUi>>>
    suspend fun wordsListFromDeck(deckId: Int): Flow<Resource<List<WordDataUi>>>
}
