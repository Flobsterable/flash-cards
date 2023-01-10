package ru.flobsterable.flashCards.presentation.screens.swipeCards.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.flobsterable.flashCards.ui.theme.BlackScrim

@Composable
fun WordCardComponent() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Cyan)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 68.dp)
                .fillMaxSize().background(BlackScrim),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = "word",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier
            )
            Text(
                text = "transcription",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier
            )
            Text(
                text = "transcription",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier
            )
            Text(
                text = "meaning_text",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier
            )
            Text(
                text = "example_text",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier
            )
        }
    }
    //name, transcription, img_path, word_sound, meaning_sound, example_sound, meaning_text, example_text,
}
