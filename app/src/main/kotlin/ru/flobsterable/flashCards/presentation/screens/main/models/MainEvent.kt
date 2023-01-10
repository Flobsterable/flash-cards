package ru.flobsterable.flashCards.presentation.screens.main.models

sealed class MainEvent {
    object OpenSwipeCards : MainEvent()
    object CheckData : MainEvent()
}
