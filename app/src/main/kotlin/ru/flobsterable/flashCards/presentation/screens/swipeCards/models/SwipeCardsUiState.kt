package ru.flobsterable.flashCards.presentation.screens.swipeCards.models

import ru.flobsterable.flashCards.presentation.models.WordDataUi

data class SwipeCardsUiState(
    val words: List<WordDataUi> = emptyList()
) {
    companion object {
        val Empty = SwipeCardsUiState()
    }
}
