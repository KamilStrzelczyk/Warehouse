package com.example.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun WhCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    if (onClick == null) {
        Card(
            modifier = modifier
                .padding(15.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
        ) {
            content()
        }
    } else {
        Card(
            onClick = onClick,
            modifier = modifier
                .padding(15.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
        ) {
            content()
        }
    }
}
