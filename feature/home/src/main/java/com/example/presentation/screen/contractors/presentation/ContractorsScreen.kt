package com.example.presentation.screen.contractors.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.Contractor
import com.example.presentation.component.WhCard
import com.example.presentation.component.WhScreenContainer
import com.example.presentation.component.fakeContractor
import com.example.presentation.screen.contractors.component.ContractorDialog
import com.example.presentation.screen.contractors.presentation.ContractorsViewModel.Event.NavigateToContractorDetails
import com.example.resources.R as ResR

@Composable
fun ContractorsScreen(
    navigateToContractorDetails: (Long) -> Unit,
) {
    val viewModel: ContractorsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is NavigateToContractorDetails -> navigateToContractorDetails(event.contractorId)
            }
        }
    }

    ContractorsScreen(
        contractors = state.contractors,
        onAddClicked = viewModel::addContractorDialogVisible,
        onContractorClicked = navigateToContractorDetails,
    )
    ContractorDialog(
        confirmButtonText = stringResource(ResR.string.contractors_add_contractor_dialog_cta_button_add),
        visible = state.dialogVisible,
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
    onContractorClicked: (Long) -> Unit,
) {
    WhScreenContainer(
        title = { Text(stringResource(ResR.string.contractors_top_bar_title)) },
        onClicked = { onAddClicked(true) },
    ) {
        LazyColumn {
            items(contractors) { contractor ->
                Item(
                    contractor = contractor,
                    onClicked = onContractorClicked,
                )
            }
        }
    }
}

@Composable
fun Item(
    contractor: Contractor,
    onClicked: (Long) -> Unit,
) {
    WhCard(onClick = { onClicked(contractor.id) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(contractor.name)
        }
    }
}

@Composable
@Preview
private fun ContractorsScreen_Preview() {
    ContractorsScreen(
        contractors = mutableListOf(fakeContractor),
        onAddClicked = {},
        onContractorClicked = {},
    )
}

@Composable
@Preview
private fun Item_Preview() {
    Item(
        contractor = fakeContractor,
        onClicked = {},
    )
}

@Composable
@Preview
private fun Dialog_Preview() {
    ContractorDialog(
        confirmButtonText = stringResource(ResR.string.contractors_add_contractor_dialog_cta_button_add),
        visible = true,
        textFieldValue = "LoremIpsum",
        onTextValueChange = {},
        onConfirmDialog = {},
        onDismissDialog = {}
    )
}
