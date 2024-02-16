package com.example.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Number
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.utils.UnitOfMeasure
import com.example.presentation.component.GoodsTextFieldType.AMOUNT
import com.example.presentation.component.GoodsTextFieldType.NAME
import com.example.resources.R

@Composable
internal fun AddGoodsDialog(
    visible: Boolean,
    textFieldValueForName: String,
    textFieldValueForAmount: String,
    selectedUnitOfMeasure: UnitOfMeasure,
    onConfirmDialog: () -> Unit,
    onDismissDialog: (Boolean) -> Unit,
    onSelectedUnitOfMeasure: (UnitOfMeasure) -> Unit,
    onTextValueChange: (String, GoodsTextFieldType) -> Unit,
) {
    GoodsDialog(
        header = "Dodaj Pozycję",
        visible = visible,
        textFieldValueForName = textFieldValueForName,
        textFieldValueForAmount = textFieldValueForAmount,
        selectedUnitOfMeasure = selectedUnitOfMeasure,
        onConfirmDialog = onConfirmDialog,
        onDismissDialog = onDismissDialog,
        onTextValueChange = onTextValueChange,
        onSelectedUnitOfMeasure = onSelectedUnitOfMeasure,
    )
}

@Composable
internal fun EditGoodsDialog(
    visible: Boolean,
    textFieldValueForName: String,
    textFieldValueForAmount: String,
    selectedUnitOfMeasure: UnitOfMeasure,
    onConfirmDialog: () -> Unit,
    onDismissDialog: (Boolean) -> Unit,
    onSelectedUnitOfMeasure: (UnitOfMeasure) -> Unit,
    onTextValueChange: (String, GoodsTextFieldType) -> Unit,
) {
    GoodsDialog(
        header = "Edytuj pozycje",
        visible = visible,
        textFieldValueForName = textFieldValueForName,
        textFieldValueForAmount = textFieldValueForAmount,
        selectedUnitOfMeasure = selectedUnitOfMeasure,
        onConfirmDialog = onConfirmDialog,
        onDismissDialog = onDismissDialog,
        onTextValueChange = onTextValueChange,
        onSelectedUnitOfMeasure = onSelectedUnitOfMeasure,
    )
}

@Composable
private fun GoodsDialog(
    header: String,
    visible: Boolean,
    textFieldValueForName: String,
    textFieldValueForAmount: String,
    selectedUnitOfMeasure: UnitOfMeasure,
    onConfirmDialog: () -> Unit,
    onDismissDialog: (Boolean) -> Unit,
    onSelectedUnitOfMeasure: (UnitOfMeasure) -> Unit,
    onTextValueChange: (String, GoodsTextFieldType) -> Unit,
) {
    WhDialog(
        visible = visible,
        onDismissRequest = {},
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Header(header)
            TextInput(
                header = "Nazwa",
                textFieldValue = textFieldValueForName,
                goodsTextFieldType = NAME,
                onTextValueChange = onTextValueChange,
            )
            TextInput(
                header = "Ilość",
                textFieldValue = textFieldValueForAmount,
                goodsTextFieldType = AMOUNT,
                onTextValueChange = onTextValueChange,
            )
            UnitOfMeasureSelector(
                selectedUnitOfMeasure = selectedUnitOfMeasure,
                onSelectedUnitOfMeasure = onSelectedUnitOfMeasure,
            )
            ButtonRow(
                onConfirmDialog = onConfirmDialog,
                onDismissDialog = onDismissDialog,
            )
        }
    }
}

@Composable
private fun Header(header: String) {
    Column {
        Text(text = header)
        WhSpacer(2.dp)
        HorizontalDivider()
    }
}

@Composable
private fun TextInput(
    header: String,
    textFieldValue: String,
    goodsTextFieldType: GoodsTextFieldType,
    onTextValueChange: (String, GoodsTextFieldType) -> Unit,
) {
    Column {
        Text(text = header)
        if (goodsTextFieldType == AMOUNT) {
            TextField(
                value = textFieldValue,
                onValueChange = { text -> onTextValueChange(text, AMOUNT) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = Number),
            )
        } else {
            TextField(
                value = textFieldValue,
                onValueChange = { text -> onTextValueChange(text, NAME) },
                singleLine = true,
            )
        }
    }
}

@Composable
private fun UnitOfMeasureSelector(
    selectedUnitOfMeasure: UnitOfMeasure,
    onSelectedUnitOfMeasure: (UnitOfMeasure) -> Unit,
) {
    var dialogVisible by remember { mutableStateOf(false) }

    WhSelector(
        header = stringResource(R.string.documents_add_document_dialog_contractor_selector),
        selectedItem = selectedUnitOfMeasure.name,
        showItemList = { dialogVisible = it }
    ) {
        UnitOfMeasureListDialog(
            dialogVisible = dialogVisible,
            onSelectedUnitOfMeasure = onSelectedUnitOfMeasure,
            onDismissRequest = { dialogVisible = false }
        )
    }
}

@Composable
private fun UnitOfMeasureListDialog(
    dialogVisible: Boolean,
    onSelectedUnitOfMeasure: (UnitOfMeasure) -> Unit,
    onDismissRequest: () -> Unit,
) {
    WhDialog(
        visible = dialogVisible,
        onDismissRequest = onDismissRequest,
    ) {
        WhCard {
            Column {
                UnitOfMeasure.list().forEach { unit ->
                    Column(
                        modifier = Modifier
                            .clickable {
                                onSelectedUnitOfMeasure(unit)
                                onDismissRequest()
                            }
                            .padding(10.dp),
                    ) {
                        Text(text = unit.name)
                        WhSpacer(10.dp)
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
private fun ButtonRow(
    onConfirmDialog: () -> Unit,
    onDismissDialog: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        WhDialogButton(
            text = stringResource(R.string.documents_add_document_dialog_cta_button_add),
            onClicked = onConfirmDialog,
            weight = 1f,
        )
        WhSpacer(5.dp)
        WhDialogButton(
            text = stringResource(R.string.documents_add_document_dialog_cta_button_cancel),
            onClicked = { onDismissDialog(false) },
            weight = 1f,
        )
    }
}

enum class GoodsTextFieldType {
    NAME,
    AMOUNT,
}

@Composable
@Preview
private fun AddGoodsDialog_Preview() {
    AddGoodsDialog(
        visible = true,
        selectedUnitOfMeasure = UnitOfMeasure.Kg,
        textFieldValueForName = "LoremIpsum",
        textFieldValueForAmount = "LoremIpsum",
        onConfirmDialog = {},
        onTextValueChange = { _, _ -> },
        onSelectedUnitOfMeasure = {},
        onDismissDialog = { },
    )
}
