package com.example.cleankt.counter

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface Counter {
    fun onIncrement()
    fun onDecrement()
}

@Composable
fun Count(count: Int) {
    Text("$count")
}

@Composable
fun CounterScreen(count: Int, counter: Counter) {
    Column {
        Count(count)

        Row {
            Button(onClick=counter::onDecrement) { Text("-") }
            Button(onClick=counter::onIncrement) { Text("+") }
        }
    }
}

@Preview
@Composable
@ExperimentalCoroutinesApi
fun PreviewCounter() {
    CounterScreen(count = 10, counter = CountInteractor())
}
