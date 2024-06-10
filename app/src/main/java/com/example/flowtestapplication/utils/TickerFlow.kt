package com.example.flowtestapplication.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration

/**
 * Creates a flow that emits [Duration] once every [period].
 * Optionally set an initial and maximum time value.
 * Will emit both the initial and maximum value after which there will be no emissions
 * e.g period = 1, initial = 0, max = 5 emissions will be [0, 1, 2, 3, 4, 5] (6 emits)
 */
fun timerFlow(
    period: Duration,
    initialTime: Duration = Duration.ZERO,
    maximumTime: Duration = Duration.INFINITE,
) = flow {
    var currentTime = initialTime
    while (currentTime <= maximumTime) {
        emit(currentTime)
        currentTime = currentTime.plus(period)
        delay(period)
    }
}