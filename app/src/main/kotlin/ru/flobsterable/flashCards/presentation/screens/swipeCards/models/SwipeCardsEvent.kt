package ru.flobsterable.flashCards.presentation.screens.swipeCards.models

sealed class SwipeCardsEvent {
    object PopBack : SwipeCardsEvent()
    object PlaySound : SwipeCardsEvent()
}
