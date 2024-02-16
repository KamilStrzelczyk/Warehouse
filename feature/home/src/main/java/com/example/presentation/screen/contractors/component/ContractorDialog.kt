package com.example.presentation.screen.contractors.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.presentation.component.WhDialog
import com.example.presentation.component.WhSpacer
import com.example.resources.R

@Composable
internal fun ContractorDialog(
    confirmButtonText: String,
    visible: Boolean,
    textFieldValue: String,
    onTextValueChange: (String) -> Unit,
    onDismissDialog: (Boolean) -> Unit,
    onConfirmDialog: () -> Unit,
) {
    WhDialog(
        visible = visible,
        onDismissRequest = {},
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = stringResource(R.string.contractors_add_contractor_dialog_contractor_name))
            TextField(
                value = textFieldValue,
                onValueChange = { text -> onTextValueChange(text) },
                singleLine = true,
            )
            WhSpacer(5.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                DialogButton(
                    text = confirmButtonText,
                    onClicked = onConfirmDialog,
                    weight = 1f,
                )
                WhSpacer(5.dp)
                DialogButton(
                    text = stringResource(R.string.contractors_add_contractor_dialog_cta_button_cancel),
                    onClicked = { onDismissDialog(false) },
                    weight = 1f,
                )
            }
        }
    }
}

@Composable
private fun RowScope.DialogButton(
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
