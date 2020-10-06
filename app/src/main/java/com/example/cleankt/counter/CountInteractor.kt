package com.example.cleankt.counter

import com.example.cleankt.Interactor
import kotlinx.coroutines.ExperimentalCoroutinesApi

fun increment(count: Int) = count + 1
fun decrement(count: Int) = count - 1

@ExperimentalCoroutinesApi
class CountInteractor : Interactor<Int>(0), Counter {
    override fun onIncrement() {
        dispatch(::increment)
    }

    override fun onDecrement() {
        dispatch(::decrement)
    }
}
