package com.example.cleankt

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

typealias Callback<T> = (T) -> Any
typealias Action<State> = suspend (State) -> State

fun <T>LifecycleOwner.observe(data: LiveData<T>, callback: Callback<T>) =
    data.observe(this, Observer { callback(it) })

@ExperimentalCoroutinesApi
fun <T>LifecycleOwner.observe(flow: Flow<T>, callback: Callback<T>) =
    lifecycleScope.launchWhenCreated {
        flow.collect { callback(it) }
    }

@ExperimentalCoroutinesApi
fun <T> Fragment.observe(flow: Flow<T>, callback: Callback<T>) =
    viewLifecycleOwner.observe(flow, callback)

@ExperimentalCoroutinesApi
open class Interactor<State>(initial: State): ViewModel() {
    private val states = MutableStateFlow(initial)
    val state: StateFlow<State> get() = states

    fun dispatch(action: Action<State>) {
        viewModelScope.launch { states.value = action(states.value) }
    }
}
