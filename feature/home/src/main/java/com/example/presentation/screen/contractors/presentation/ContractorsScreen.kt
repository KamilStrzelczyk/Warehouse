package com.example.presentation.screen.contractors.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Contractor
import com.example.presentation.component.WhCard
import com.example.presentation.component.WhDialog
import com.example.presentation.component.WhScreenContainer
import com.example.presentation.component.WhSpacer
import com.example.presentation.component.fakeContractor
import com.example.resources.R as ResR

@Composable
fun ContractorsScreen() {
    val viewModel: ContractorsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    ContractorsScreen(
        contractors = state.contractors,
        onAddClicked = viewModel::addContractorDialogVisible,
    )
    AddContractorDialog(
        dialogVisible = state.dialogVisible,
        textFieldValue = state.textFieldValue,
        onTextValueChange = { text -> viewModel.onTextValueChange(text) },
        onDismissDialog = viewModel::addContractorDialogVisible,
        onConfirmDialog = viewModel::onAddContractorClicked,
    )
}

@Composable
private fun ContractorsScreen(
    contractors: List<Contractor>,
    onAddClicked: (Boolean) -> Unit,
) {
    WhScreenContainer(
        title = stringResource(ResR.string.contractors_top_bar_title),
        onClicked = { onAddClicked(true) },
    ) {
        LazyColumn {
            items(contractors) { contractor ->
                Item(
                    contractor = contractor,
                    onMoreClicked = {},
                )
            }
        }
    }
}

@Composable
fun Item(
    contractor: Contractor,
    onMoreClicked: () -> Unit,
) {
    WhCard(onClick = {}) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(contractor.name)
            Icon(
                Icons.Default.MoreVert,
                contentDescription = null,
                modifier = Modifier.clickable { onMoreClicked() },
            )
        }
    }
}

@Composable
private fun AddContractorDialog(
    dialogVisible: Boolean,
    textFieldValue: String,
    onTextValueChange: (String) -> Unit,
    onDismissDialog: (Boolean) -> Unit,
    onConfirmDialog: () -> Unit,
) {
    WhDialog(
        visible = dialogVisible,
        onDismissRequest = {},
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = stringResource(ResR.string.contractors_add_contractor_dialog_contractor_name))
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
                    text = stringResource(ResR.string.contractors_add_contractor_dialog_cta_button_add),
                    onClicked = onConfirmDialog,
                    weight = 1f,
                )
                WhSpacer(5.dp)
                DialogButton(
                    text = stringResource(ResR.string.contractors_add_contractor_dialog_cta_button_cancel),
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

@Composable
@Preview
private fun ContractorsScreen_Preview() {
    ContractorsScreen(
        contractors = mutableListOf(fakeContractor),
        onAddClicked = {},
    )
}

@Composable
@Preview
private fun Item_Preview() {
    Item(
        contractor = fakeContractor,
        onMoreClicked = {},
    )
}

@Composable
@Preview
private fun Dialog_Preview() {
    AddContractorDialog(
        dialogVisible = true,
        textFieldValue = "LoremIpsum",
        onTextValueChange = {},
        onConfirmDialog = {},
        onDismissDialog = {}
    )
}
