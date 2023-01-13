package ru.flobsterable.flashCards.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.flobsterable.flashCards.data.database.entity.DeckWithWords
import ru.flobsterable.flashCards.data.database.entity.DecksEntity
import ru.flobsterable.flashCards.data.database.entity.WordsDeckEntity

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDeck(deck: DecksEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWords(words: List<WordsDeckEntity>)

    @Query("SELECT* FROM deck_table ORDER BY name")
    fun getDeckList(): Flow<List<DecksEntity>>

    @Transaction
    @Query("SELECT * FROM deck_table WHERE id = :deckId")
    fun getDeckWithWords(deckId: Int): Flow<DeckWithWords>
}
