package com.example.cleankt.counter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.lifecycleScope
import com.example.cleankt.View
import com.example.cleankt.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CounterActivity: AppCompatActivity(), View<Int> {
    private val interactor by viewModels<CountInteractor>()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch { observe(interactor, ::render) }
    }

    override fun render(model: Int) {
        setContent {
            MaterialTheme {
                CounterScreen(count=model, counter=interactor)
            }
        }
    }
}
