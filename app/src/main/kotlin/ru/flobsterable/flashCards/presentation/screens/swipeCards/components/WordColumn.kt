package ru.flobsterable.flashCards.presentation.screens.swipeCards.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.flobsterable.flashCards.R
import ru.flobsterable.flashCards.presentation.models.WordDataUi
import ru.flobsterable.flashCards.ui.consts.wordColumnPadding

@Composable
fun WordColumn(wordData: WordDataUi, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(wordColumnPadding)
    ) {
        Text(
            text = wordData.word,
            style = MaterialTheme.typography.displayMedium,
        )
        Row() {
            Text(
                text = wordData.transcription,
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                shape = CircleShape,
                onClick = { onClick(wordData.wordSoundPath) }
            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = stringResource(id = R.string.cd_play_button)
                )
            }
        }
        Text(
            text = wordData.meaningText,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(6.dp),
        )
        Button(
            shape = CircleShape,
            onClick = { onClick(wordData.meaningSoundPath) }
        ) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = stringResource(id = R.string.cd_play_button)
            )
        }
        Text(
            text = wordData.exampleText,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(6.dp),
        )
        Button(
            shape = CircleShape,
            onClick = { onClick(wordData.exampleSoundPath) }
        ) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = stringResource(id = R.string.cd_play_button)
            )
        }
    }
}
