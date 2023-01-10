package ru.flobsterable.flashCards.presentation.screens.main.models

data class MainUiState(
    val dataTitleName: String? = null,
) {
    companion object {
        val Empty = MainUiState()
    }
}
