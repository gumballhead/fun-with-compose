package com.example.cleankt

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

typealias Callback<T> = (T) -> Any
typealias Action<State> = suspend (State) -> State

fun <T>LifecycleOwner.observe(data: LiveData<T>, callback: Callback<T>) =
    data.observe(this, Observer { callback(it) })

@ExperimentalCoroutinesApi
suspend fun <T>LifecycleOwner.observe(interactor: Interactor<T>, callback: Callback<T>) =
    interactor.states.collect { callback(it) }

@ExperimentalCoroutinesApi
open class Interactor<State>(initial: State): ViewModel() {
    private val _states = MutableStateFlow(initial)

    val states: StateFlow<State> get() = _states

    fun dispatch(action: Action<State>) {
        viewModelScope.launch { _states.value = action(_states.value) }
    }
}
