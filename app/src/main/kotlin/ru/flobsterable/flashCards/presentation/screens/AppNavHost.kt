package ru.flobsterable.flashCards.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.flobsterable.flashCards.navigation.AppScreens
import ru.flobsterable.flashCards.presentation.screens.main.MainScreen
import ru.flobsterable.flashCards.presentation.screens.main.models.MainViewModel
import ru.flobsterable.flashCards.presentation.screens.swipeCards.SwipeCardsScreen
import ru.flobsterable.flashCards.presentation.screens.swipeCards.models.SwipeCardsViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.MainScreen.route
    ) {
        composable(route = AppScreens.MainScreen.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(viewModel)
        }
        composable(
            route = AppScreens.SwipeCardsScreen.route
        ) {
            val viewModel = hiltViewModel<SwipeCardsViewModel>()
            SwipeCardsScreen()
        }
    }
}
