package ru.flobsterable.flashCards.presentation.screens.swipeCards.models

data class SwipeCardsUiState(
    val words: List<WordDataUi> = emptyList()
) {
    companion object {
        val Empty = SwipeCardsUiState()
    }
}
