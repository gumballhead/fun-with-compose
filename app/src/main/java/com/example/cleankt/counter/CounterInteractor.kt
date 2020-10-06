package com.example.cleankt.counter

import com.example.cleankt.Interactor
import kotlinx.coroutines.ExperimentalCoroutinesApi

fun increment(count: Int) = count + 1
fun decrement(count: Int) = count - 1

@ExperimentalCoroutinesApi
class CounterInteractor : Counter, Interactor<Int>(0) {
    override fun increment() {
        dispatch(::increment)
    }

    override fun decrement() {
        dispatch(::decrement)
    }
}
