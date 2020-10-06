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
import java.text.NumberFormat

interface Counter {
    fun increment()
    fun decrement()
}

fun formatCount(count: Int): String =
    NumberFormat.getNumberInstance().format(count)

@Composable
fun Count(count: Int) {
    Text(formatCount(count), fontSize = TextUnit.Companion.Sp(36))
}

@ExperimentalCoroutinesApi
@Composable
fun CounterScreen(count: Int, counter: Counter) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Count(count)
        Spacer(Modifier.preferredHeight(16.dp))

        Row {
            Button(onClick=counter::decrement) {
                Text("-")
            }

            Button(onClick=counter::increment) {
                Text("+")
            }
        }
    }
}

@Preview
@Composable
@ExperimentalCoroutinesApi
fun PreviewCounter() {
    CounterScreen(count=1234, counter=CounterInteractor())
}
