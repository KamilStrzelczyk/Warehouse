package com.example.home.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    Row {
        Button(onClick = {}) {
            Text(text = "Lista dokumentów")
        }
        Button(onClick = {}) {
            Text(text = "Lista kontrahenci")
        }
    }
}