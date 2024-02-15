package com.example.presentation.screen.documentdetails

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.Document
import com.example.domain.model.Goods
import com.example.domain.utils.UnitOfMeasure
import com.example.presentation.component.WhCard
import com.example.presentation.component.WhDialog
import com.example.presentation.component.WhDialogButton
import com.example.presentation.component.WhScreenContainer
import com.example.presentation.component.WhSpacer
import com.example.presentation.component.fakeDocument
import com.example.presentation.component.fakeGoods
import com.example.presentation.screen.documentdetails.DocumentDetailsViewModel.ViewModelState.GoodsTextField
import com.example.presentation.screen.documentdetails.DocumentDetailsViewModel.ViewModelState.GoodsTextField.AMOUNT
import com.example.presentation.screen.documentdetails.DocumentDetailsViewModel.ViewModelState.GoodsTextField.NAME
import com.example.resources.R

@Composable
internal fun DocumentDetailsScreen() {
    val viewModel: DocumentDetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    DocumentDetailsScreen(
        goods = state.goods,
        onClicked = {},
        onAddGoodsClicked = viewModel::addGoodsDialogVisible,
    )
    AddGoodsDialog(
        selectedUnitOfMeasure = state.selectedUnitOfMeasure,
        textFieldValueForName = state.textFieldValueForName,
        textFieldValueForAmount = state.textFieldValueForAmount,
        visible = state.addGoodsDialogVisible,
        onConfirmDialog = {},
        onTextValueChange = viewModel::onTextValueChange,
        onDismissDialog = viewModel::addGoodsDialogVisible,
    )
}

@Composable
internal fun DocumentDetailsScreen(
    goods: List<Goods>,
    onClicked: () -> Unit,
    onAddGoodsClicked: (Boolean) -> Unit,
) {
    WhScreenContainer(
        title = header(fakeDocument),
        onClicked = { onAddGoodsClicked(true) },
    ) {
        LazyColumn {
            itemsIndexed(goods) { index, it ->
                Item(
                    index = index + 1,
                    goods = it,
                    onClicked = onClicked,
                )
            }
        }
    }
}

@Composable
private fun Item(
    index: Int,
    goods: Goods,
    onClicked: () -> Unit,
) {
    WhCard(onClick = onClicked) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "$index.")
            WhSpacer(spacer = 3.dp)
            Column {
                goods.run {
                    Text(text = name)
                    HorizontalDivider(modifier = Modifier.padding(2.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(text = unitOfMeasure.name)
                        Text(text = ": ")
                        Text(text = amount)
                    }
                }
            }
        }
    }
}

@Composable
private fun header(document: Document): String =
    document.run {
        "$date $signature ${contractor.name}"
    }

@Composable
private fun AddGoodsDialog(
    visible: Boolean,
    textFieldValueForName: String,
    textFieldValueForAmount: String,
    selectedUnitOfMeasure: UnitOfMeasure,
    onConfirmDialog: () -> Unit,
    onDismissDialog: (Boolean) -> Unit,
    onTextValueChange: (String, GoodsTextField) -> Unit,
) {
    WhDialog(
        visible = visible,
        onDismissRequest = {},
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Dodaj pozycję")
            HorizontalDivider()
            Text(text = "Nazwa")
            TextField(
                value = textFieldValueForName,
                onValueChange = { text -> onTextValueChange(text, NAME) },
                singleLine = true,
            )
            WhSpacer(5.dp)
            Text(text = "Jednostka miary")
            UnitOfMeasureSelector(
                selectedUnitOfMeasure = selectedUnitOfMeasure,
                onSelectedContractor = {})
            Text(text = "Ilość")
            TextField(
                value = textFieldValueForAmount,
                onValueChange = { text -> onTextValueChange(text, AMOUNT) },
                singleLine = true,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
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
    }
}

@Composable
private fun UnitOfMeasureSelector(
    selectedUnitOfMeasure: UnitOfMeasure,
    onSelectedContractor: (UnitOfMeasure) -> Unit,
) {
    var dialogVisible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.LightGray,
            )
            .clickable { dialogVisible = !dialogVisible }
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = selectedUnitOfMeasure.name)
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
        )
    }
    UnitOfMeasureListDialog(
        dialogVisible = dialogVisible,
        onSelectedContractor = onSelectedContractor,
        onDismissRequest = { dialogVisible = false }
    )
}

@Composable
private fun UnitOfMeasureListDialog(
    dialogVisible: Boolean,
    onSelectedContractor: (UnitOfMeasure) -> Unit,
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
                                onSelectedContractor(unit)
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
@Preview
private fun DocumentDetailsScreen_Preview() {
    DocumentDetailsScreen(
        goods = mutableListOf(fakeGoods),
        onClicked = {},
        onAddGoodsClicked = {},
    )
}

@Composable
@Preview
private fun Item_Preview() {
    Item(
        index = 10,
        goods = fakeGoods,
        onClicked = {},
    )
}

@Composable
@Preview
private fun AddGoodsDialog_Preview() {
    AddGoodsDialog(
        selectedUnitOfMeasure = UnitOfMeasure.Kg,
        textFieldValueForName = "LoremIpsum",
        textFieldValueForAmount = "LoremIpsum",
        visible = true,
        onConfirmDialog = {},
        onTextValueChange = { _, _ -> },
        onDismissDialog = { },
    )
}
