package ru.flobsterable.flashCards.navigation

const val DETAIL_ARGUMENT_KEY = "deckId"

sealed class AppScreens(
    val route: String
) {
    object MainScreen : AppScreens("MainScreen")
    object SwipeCardsScreen : AppScreens("SwipeCardsScreen")
}
