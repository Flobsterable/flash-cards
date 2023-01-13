package ru.flobsterable.flashCards.presentation.screens.main.models

import ru.flobsterable.flashCards.presentation.models.DeckDataUi

data class MainUiState(
    val deckData: DeckDataUi? = null
) {
    companion object {
        val Empty = MainUiState()
    }
}
