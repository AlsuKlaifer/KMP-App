package com.example.newsapp.core.vm

import com.example.newsapp.core.CommonFlow
import com.example.newsapp.core.CommonStateFlow
import com.example.newsapp.core.asCommonStateFlow
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : Any, Action, Event>(
    initState: State,
) : KmpViewModel() {

    private val _action =
        MutableSharedFlow<Action>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val _viewState = MutableStateFlow(initState)

    protected var viewState: State
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    protected var viewAction: Action?
        get() = if (_action.replayCache.isNotEmpty()) {
            _action.replayCache.last()
        } else {
            null
        }
        set(value) {
            scope.launch {
                if (value != null) {
                    _action.emit(value)
                } else {
                    _action.resetReplayCache()
                }
            }
        }

    val viewStates: CommonStateFlow<State>
        get() = _viewState.asStateFlow().asCommonStateFlow()

    // todo add common flow for ios
    // вроде добавил НАДО ТЕСТИТЬ
    val viewActions: CommonFlow<Action>
        get() = CommonFlow(_action.asSharedFlow())

    abstract fun obtainEvent(event: Event)
}