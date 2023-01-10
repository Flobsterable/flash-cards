package ru.flobsterable.flashCards.navigation

import androidx.navigation.NavHostController

interface AppNavigation {
    var navHostController: NavHostController?

    fun navigateTo(appScreen: AppScreens)
    fun popBackStack()
}
