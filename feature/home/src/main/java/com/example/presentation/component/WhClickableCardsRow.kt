package com.example.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun WhClickableCardsRow(
    firstButtonText: String,
    secondButtonText: String,
    onFirstButtonClicked: (Boolean) -> Unit,
    onSecondButtonClicked: () -> Unit,
) {
    Row(modifier = Modifier) {
        ClickableCard(
            text = firstButtonText,
            onClicked = { onFirstButtonClicked(true) },
        )
        ClickableCard(
            text = secondButtonText,
            onClicked = onSecondButtonClicked,
        )
    }
}

@Composable
private fun RowScope.ClickableCard(
    text: String,
    onClicked: () -> Unit,
) {
    WhCard(
        modifier = Modifier.weight(1f),
        onClick = onClicked,
    ) {
        Box(
            modifier = Modifier.padding(vertical = 50.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                textAlign = TextAlign.Center,
            )
        }
    }
}