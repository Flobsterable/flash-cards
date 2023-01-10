package ru.flobsterable.flashCards.presentation.screens.main.components

import androidx.compose.runtime.Composable
import ru.flobsterable.flashCards.presentation.screens.components.TextButton

@Composable
fun MainComponent(titleText: String, buttonText: String, onClick: () -> Unit) {
    TitleText(text = titleText)
    TextButton(text = buttonText, onClick = onClick)
}
