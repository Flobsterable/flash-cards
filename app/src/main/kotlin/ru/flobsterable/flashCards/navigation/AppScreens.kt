package ru.flobsterable.flashCards.navigation

sealed class AppScreens(
    val route: String
) {
    object MainScreen : AppScreens("MainScreen")
    object SwipeCardsScreen : AppScreens("SwipeCardsScreen")
}
