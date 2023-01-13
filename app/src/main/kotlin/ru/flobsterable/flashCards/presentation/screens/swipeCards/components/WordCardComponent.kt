package ru.flobsterable.flashCards.presentation.screens.swipeCards.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import ru.flobsterable.flashCards.R
import ru.flobsterable.flashCards.presentation.models.WordDataUi
import ru.flobsterable.flashCards.ui.consts.WORD_IMAGE_WEIGHT

@Composable
fun WordCardComponent(wordData: WordDataUi, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = wordData.imagePath
                ),
                contentDescription = stringResource(id = R.string.cd_image_word),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(WORD_IMAGE_WEIGHT)
            )
            WordColumn(wordData = wordData, onClick = onClick)
        }
    }
}
