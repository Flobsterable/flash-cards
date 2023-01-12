package ru.flobsterable.flashCards.presentation.screens.swipeCards.models

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.flobsterable.flashCards.navigation.AppNavigation
import ru.flobsterable.flashCards.presentation.screens.models.WordDataUi
import javax.inject.Inject

@HiltViewModel
class SwipeCardsViewModel @Inject constructor(
    private val navigation: AppNavigation,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SwipeCardsUiState.Empty)
    val uiState: StateFlow<SwipeCardsUiState> = _uiState.asStateFlow()

    fun sendEvent(event: SwipeCardsEvent) {
        when (event) {
            SwipeCardsEvent.PlaySound -> playSound("")
            SwipeCardsEvent.PopBack -> popBack()
        }
    }

    init {
        getWords()
    }

    private fun getWords() {
        val testList = listOf(
            WordDataUi("test", "test", "test", "test"),
            WordDataUi("test2", "test2", "test2", "test2"),
            WordDataUi("test3", "test3", "test3", "test3")
        )

        _uiState.update { it.copy(words = testList) }
    }

    private fun playSound(url: String) {}

    private fun popBack() {
        navigation.popBackStack()
    }
}
