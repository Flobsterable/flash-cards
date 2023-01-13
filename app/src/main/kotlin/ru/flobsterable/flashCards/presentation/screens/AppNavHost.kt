package ru.flobsterable.flashCards.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.flobsterable.flashCards.navigation.AppScreens
import ru.flobsterable.flashCards.navigation.DETAIL_ARGUMENT_KEY
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
            route = "${AppScreens.SwipeCardsScreen.route}/{$DETAIL_ARGUMENT_KEY}",
            arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) { type = NavType.IntType }),
        ) {
            val deckId = it.arguments?.getInt(DETAIL_ARGUMENT_KEY)!!.toInt()
            val viewModel = hiltViewModel<SwipeCardsViewModel>()
            SwipeCardsScreen(viewModel, id = deckId)
        }
    }
}
