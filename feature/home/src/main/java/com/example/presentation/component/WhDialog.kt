package com.example.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
internal fun WhDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (!visible) return
    Dialog(onDismissRequest = onDismissRequest) {
        WhCard {
            content()
        }
    }
}
