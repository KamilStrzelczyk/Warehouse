package com.example.presentation.screen.contractorDetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.Contractor
import com.example.presentation.component.WhClickableCardsRow
import com.example.presentation.component.WhScreenContainer
import com.example.presentation.component.fakeContractor
import com.example.presentation.screen.contractorDetails.presentation.ContractorDetailsViewModel.Event.NavigateBack
import com.example.presentation.screen.contractors.component.ContractorDialog
import com.example.resources.R as ResR

@Composable
fun ContractorDetailsScreen(
    navigateBack: () -> Unit,
) {
    val viewModel: ContractorDetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.event.collect { event ->
            when (event) {
                NavigateBack -> navigateBack()
            }
        }
    }

    ContractorsScreen(
        contractor = state.contractor,
        onDeleteClicked = viewModel::onDeleteContractorClicked,
        onEditClicked = viewModel::editContractorDialogVisible,
    )
    ContractorDialog(
        confirmButtonText = stringResource(ResR.string.contractors_add_contractor_dialog_cta_button_add),
        visible = state.dialogVisible,
        textFieldValue = state.textFieldValue,
        onTextValueChange = { text -> viewModel.onTextValueChange(text) },
        onDismissDialog = viewModel::editContractorDialogVisible,
        onConfirmDialog = viewModel::editContractor,
    )
}

@Composable
private fun ContractorsScreen(
    contractor: Contractor,
    onDeleteClicked: () -> Unit,
    onEditClicked: (Boolean) -> Unit,
) {
    WhScreenContainer(title = { Text(stringResource(ResR.string.contractor_details_top_bar_title)) }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                DetailsRow(
                    title = "Nazwa kontrahenta",
                    content = contractor.name,
                )
                DetailsRow(
                    title = "Sygnatura",
                    content = contractor.signature,
                )
            }
            WhClickableCardsRow(
                firstButtonText = "EDYTUJ",
                secondButtonText = "USUN",
                onFirstButtonClicked = onEditClicked,
                onSecondButtonClicked = onDeleteClicked,
            )
        }
    }
}

@Composable
private fun DetailsRow(
    title: String,
    content: String,
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = title)
        Text(text = content)
    }
}

@Composable
@Preview
private fun ContractorsScreen_Preview() {
    ContractorsScreen(
        contractor = fakeContractor,
        onEditClicked = {},
        onDeleteClicked = {},
    )
}
