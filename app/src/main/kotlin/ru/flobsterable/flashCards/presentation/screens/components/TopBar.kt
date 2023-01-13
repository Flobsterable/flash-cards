package ru.flobsterable.flashCards.presentation.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.flobsterable.flashCards.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onBackClick: () -> Unit) {
    TopAppBar(title = {}, navigationIcon = {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.cd_button_back)
            )
        }
    })
}
