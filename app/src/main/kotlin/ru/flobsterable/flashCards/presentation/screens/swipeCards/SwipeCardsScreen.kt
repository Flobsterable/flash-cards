package ru.flobsterable.flashCards.presentation.screens.swipeCards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.lhoyong.swiper.Swiper
import com.github.lhoyong.swiper.rememberSwiperState
import ru.flobsterable.flashCards.R
import ru.flobsterable.flashCards.presentation.screens.components.TopBar
import ru.flobsterable.flashCards.presentation.screens.swipeCards.components.WordCardComponent
import ru.flobsterable.flashCards.presentation.screens.swipeCards.models.SwipeCardsEvent
import ru.flobsterable.flashCards.presentation.screens.swipeCards.models.SwipeCardsViewModel
import ru.flobsterable.flashCards.ui.consts.swiperPadding

@Composable
fun SwipeCardsScreen(viewModel: SwipeCardsViewModel, id: Int) {
    val stateUi = viewModel.uiState.collectAsState()
    val swiperState = rememberSwiperState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.sendEvent(SwipeCardsEvent.GetWords(id))
    })

    TopBar {
        viewModel.sendEvent(SwipeCardsEvent.PopBack)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.title_text_end),
            style = MaterialTheme.typography.headlineMedium,
        )
    }
    Swiper(
        count = stateUi.value.words.size,
        modifier = Modifier
            .fillMaxSize()
            .padding(swiperPadding),
        state = swiperState,
        onSwiped = {}
    ) { index ->
        WordCardComponent(wordData = stateUi.value.words[index]) {
            viewModel.sendEvent(SwipeCardsEvent.PlaySound(it))
        }
    }
}
