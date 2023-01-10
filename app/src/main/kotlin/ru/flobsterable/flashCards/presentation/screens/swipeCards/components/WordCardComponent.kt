package ru.flobsterable.flashCards.presentation.screens.swipeCards.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import ru.flobsterable.flashCards.presentation.screens.swipeCards.models.WordDataUi

private val padding = PaddingValues(6.dp)

@Composable
fun WordCardComponent(wordData: WordDataUi, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 68.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            SampleRow(
                text = wordData.word,
                textStyle = MaterialTheme.typography.headlineLarge) {
                onClick("")
            }
            Text(
                text = wordData.transcription,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(padding),
            )
            SampleRow(
                text = wordData.meaningText,
                textStyle = MaterialTheme.typography.bodyLarge) {
                onClick("")
            }
            SampleRow(
                text = wordData.exampleText,
                textStyle = MaterialTheme.typography.bodyLarge,) {
                onClick("")
            }
        }
    }
}

@Composable
fun SampleRow(text: String, textStyle: TextStyle, onClick: () -> Unit) {
    Row() {
        Text(
            text = text,
            style = textStyle,
            color = Color.White,
            modifier = Modifier.padding(padding),
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(shape = CircleShape, onClick = onClick) {
            Icon(Icons.Default.PlayArrow, contentDescription = "")
        }
    }
}
