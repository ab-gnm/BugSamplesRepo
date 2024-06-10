package com.example.flowtestapplication.screen1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowtestapplication.utils.timerFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds

class Screen1ViewModel : ViewModel() {

    init {
        Log.d("ASDFG", "init 1")
    }

    private val mutableStateFlow = MutableStateFlow(100)
    val flow = mutableStateFlow.asStateFlow()

    val tick1 = timerFlow(3.seconds)
        .onEach { Log.d("ASDFG", "pre-combine 1: $it") }
        .combine(mutableStateFlow) { timer, base ->
            timer * base
        }
        .onEach { Log.d("ASDFG", "emitting 1 combine: $it") }
        .stateIn(viewModelScope, SharingStarted.Lazily, -1)

    fun increment() {
        mutableStateFlow.value++
    }
}