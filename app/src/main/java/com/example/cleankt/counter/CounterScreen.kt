package com.example.cleankt.counter

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface Counter {
    fun onIncrement()
    fun onDecrement()
}

@Composable
fun Count(count: Int) {
    Text("$count",
        fontSize = TextUnit.Companion.Sp(36)
    )
}

@Composable
fun CounterScreen(count: Int, counter: Counter) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Count(count)
        Spacer(Modifier.preferredHeight(16.dp ))

        Row {
            Button(onClick=counter::onDecrement) {
                Text("-")
            }

            Button(onClick=counter::onIncrement) {
                Text("+")
            }
        }
    }
}

@Preview
@Composable
@ExperimentalCoroutinesApi
fun PreviewCounter() {
    CounterScreen(count=10, counter=CountInteractor())
}
