package ru.flobsterable.flashCards.presentation.screens.swipeCards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.theapache64.twyper.Twyper
import com.github.theapache64.twyper.rememberTwyperController
import ru.flobsterable.flashCards.navigation.AppNavigationImpl
import ru.flobsterable.flashCards.presentation.screens.swipeCards.components.WordCardComponent
import ru.flobsterable.flashCards.presentation.screens.swipeCards.models.SwipeCardsEvent
import ru.flobsterable.flashCards.presentation.screens.swipeCards.models.SwipeCardsViewModel
import ru.flobsterable.flashCards.ui.theme.FlashСardsTheme

@Composable
fun SwipeCardsScreen(viewModel: SwipeCardsViewModel) {

    val stateUi = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val twyperController = rememberTwyperController()
        Twyper(
            modifier = Modifier.padding(60.dp),
            items = stateUi.value.words,
            twyperController = twyperController,
            onItemRemoved = { item, direction -> },
            onEmpty = {
                println("End reached")
            }
        ) { item ->
            WordCardComponent(item) {
                viewModel.sendEvent(SwipeCardsEvent.PlaySound)
            }
        }
    }
}

@Composable
@Preview
fun test() {
    FlashСardsTheme {
        val viewModel = SwipeCardsViewModel(navigation = AppNavigationImpl())
        SwipeCardsScreen(viewModel)}
}
