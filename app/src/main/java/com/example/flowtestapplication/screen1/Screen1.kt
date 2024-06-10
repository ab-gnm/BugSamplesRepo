package com.example.flowtestapplication.screen1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Screen1(
    viewModel: Screen1ViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxSize().background(Color.Green)
    ) {
        Text(
            text = viewModel.tick1.collectAsState().value.toString(),
        )
        Text(text = viewModel.flow.collectAsState().value.toString())

        Button(onClick = viewModel::increment) {
            Text(text = "Increment")
        }
        Button(onClick = onClick) {
            Text(text = "Go to screen 2")
        }
    }
}