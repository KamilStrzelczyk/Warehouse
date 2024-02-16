package com.example.presentation.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun RowScope.WhDialogButton(
    text: String,
    weight: Float,
    onClicked: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight),
        onClick = onClicked,
    ) {
        Text(text = text)
    }
}
