package com.example.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun WHCard(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier
            .padding(15.dp)
            .fillMaxWidth()
            .clickable { onClicked() },
        shape = RoundedCornerShape(5.dp),
    ) {
        content()
    }
}
