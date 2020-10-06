package com.example.cleankt.counter

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.setContent
import com.example.cleankt.View
import com.example.cleankt.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CounterActivity: AppCompatActivity(), View<Int> {
    private val interactor by viewModels<CounterInteractor>()

    init {
        observe(interactor.state, ::render)
    }

    override fun render(model: Int) {
        setContent {
            MaterialTheme {
                CounterScreen(count=model, counter=interactor)
            }
        }
    }
}
