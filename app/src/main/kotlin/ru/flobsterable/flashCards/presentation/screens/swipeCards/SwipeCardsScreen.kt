package ru.flobsterable.flashCards.presentation.screens.swipeCards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.theapache64.twyper.Twyper
import com.github.theapache64.twyper.rememberTwyperController
import ru.flobsterable.flashCards.presentation.screens.swipeCards.components.WordCardComponent

@Composable
fun SwipeCardsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val items = remember { mutableStateListOf(*('A'..'C').toList().toTypedArray()) }
        val twyperController = rememberTwyperController()
        Twyper(
            modifier = Modifier.padding(60.dp),
            items = items,
            twyperController = twyperController,
            onItemRemoved = { item, direction ->
                println("Item removed: $item -> $direction")
                items.remove(item)
            },
            onEmpty = {
                println("End reached")
            }
        ) { item ->
            WordCardComponent()
        }
    }
}

@Composable
@Preview
fun test() {
    SwipeCardsScreen()
}
