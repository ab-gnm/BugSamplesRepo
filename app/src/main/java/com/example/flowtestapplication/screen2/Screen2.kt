package com.example.flowtestapplication.screen2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.flowtestapplication.screen1.Screen1ViewModel

@Composable
fun Screen2(
    viewModel: Screen2ViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.background(Color.Red),
    ) {
        Text(
            text = viewModel.tick2.collectAsState(-1).value.toString(),
        )
        Button(onClick = onClick) {
            Text(text = "Go to screen 1")
        }
    }
}