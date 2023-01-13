package ru.flobsterable.flashCards.presentation.screens.main.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.flobsterable.flashCards.data.models.Resource
import ru.flobsterable.flashCards.data.repository.Repository
import ru.flobsterable.flashCards.navigation.AppNavigation
import ru.flobsterable.flashCards.navigation.AppScreens
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigation: AppNavigation,
    private val repository: Repository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState.Empty)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        checkData()
    }

    fun sendEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OpenSwipeCards -> openSwipeCards(event.deckId)
            MainEvent.CheckData -> checkData()
        }
    }

    private fun openSwipeCards(deckId: Int) {
        navigation.navigateTo(AppScreens.SwipeCardsScreen, deckId.toString())
    }

    private fun checkData() {
        viewModelScope.launch {
            repository.getDeckList().collect { resource ->
                when (resource) {
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        val deckList = resource.data
                        _uiState.update {
                            it.copy(deckData = deckList[0])
                        }
                    }
                }
            }
        }
    }
}
