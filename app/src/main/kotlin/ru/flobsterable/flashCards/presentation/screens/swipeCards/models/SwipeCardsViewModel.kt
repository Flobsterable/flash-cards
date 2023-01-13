package ru.flobsterable.flashCards.presentation.screens.swipeCards.models

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.LifecycleObserver
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
import javax.inject.Inject

private const val SAMPLE_NUMBER = 20

@HiltViewModel
class SwipeCardsViewModel @Inject constructor(
    private val navigation: AppNavigation,
    private val repository: Repository,
) : ViewModel(), LifecycleObserver {

    private val _uiState = MutableStateFlow(SwipeCardsUiState.Empty)
    val uiState: StateFlow<SwipeCardsUiState> = _uiState.asStateFlow()

    private val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    fun sendEvent(event: SwipeCardsEvent) {
        when (event) {
            is SwipeCardsEvent.GetWords -> getWords(event.deckId)
            is SwipeCardsEvent.PlaySound -> playSound(event.mediaPath)
            SwipeCardsEvent.PopBack -> popBack()
            SwipeCardsEvent.StopPlay -> stopSound()
        }
    }

    private fun getWords(id: Int) {
        viewModelScope.launch {
            repository.wordsListFromDeck(id).collect { resource ->
                when (resource) {
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        val data = resource.data
                        _uiState.update { it.copy(words = data.shuffled().take(SAMPLE_NUMBER)) }
                    }
                }
            }
        }
    }

    private fun playSound(mediaPath: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(mediaPath)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    private fun stopSound() {
        mediaPlayer.reset()
    }

    private fun popBack() {
        navigation.popBackStack()
    }
}
