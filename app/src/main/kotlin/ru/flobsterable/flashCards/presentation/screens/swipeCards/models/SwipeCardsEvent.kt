package ru.flobsterable.flashCards.presentation.screens.swipeCards.models

sealed class SwipeCardsEvent {
    object PopBack : SwipeCardsEvent()
    data class GetWords(val deckId: Int) : SwipeCardsEvent()
    data class PlaySound(val mediaPath: String) : SwipeCardsEvent()
}
