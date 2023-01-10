package ru.flobsterable.flashCards.presentation.screens.main.models

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.flobsterable.flashCards.navigation.AppNavigation
import ru.flobsterable.flashCards.navigation.AppScreens
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigation: AppNavigation,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState.Empty)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        checkData()
    }

    fun sendEvent(event: MainEvent) {
        when (event) {
            MainEvent.OpenSwipeCards -> openSwipeCards()
            MainEvent.CheckData -> checkData()
        }
    }

    private fun openSwipeCards() {
        navigation.navigateTo(AppScreens.SwipeCardsScreen)
    }

    private fun checkData() {}
}
