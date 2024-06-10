package com.example.flowtestapplication.screen2

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.flowtestapplication.utils.timerFlow
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.seconds

class Screen2ViewModel : ViewModel() {
    init {
        Log.d("ASDFG", "init 2")
    }

    val tick2 = timerFlow(5.seconds, initialTime = 10.seconds)
        .onEach { Log.d("ASDFG", "emitting 2") }
//        .stateIn(viewModelScope, SharingStarted.Lazily, -1)
}