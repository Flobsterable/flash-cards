package ru.flobsterable.flashCards.presentation.screens.swipeCards.models

sealed class SwipeCardsEvent {
    object PopBack : SwipeCardsEvent()
    object StopPlay : SwipeCardsEvent()
    data class GetWords(val deckId: Int) : SwipeCardsEvent()
    data class PlaySound(val mediaPath: String) : SwipeCardsEvent()
}
