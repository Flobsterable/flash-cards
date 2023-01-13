package ru.flobsterable.flashCards.presentation.screens.main.models

sealed class MainEvent {
    data class OpenSwipeCards(val deckId: Int) : MainEvent()
    object CheckData : MainEvent()
}
