package com.example.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun WhSpacer(spacer: Dp) {
    Spacer(modifier = Modifier.padding(spacer))
}
