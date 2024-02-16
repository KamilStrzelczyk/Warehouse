package com.example.presentation.screen.documents.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.Contractor
import com.example.domain.model.Document
import com.example.presentation.component.WhCard
import com.example.presentation.component.WhDialog
import com.example.presentation.component.WhDialogButton
import com.example.presentation.component.WhScreenContainer
import com.example.presentation.component.WhSelector
import com.example.presentation.component.WhSpacer
import com.example.presentation.component.fakeDocument
import com.example.resources.R as ResR

@Composable
fun DocumentsScreen(
    navigateToDocumentDetails: (Long) -> Unit,
) {
    val viewModel: DocumentsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    DocumentsScreen(
        documents = state.documents,
        onDocumentClicked = navigateToDocumentDetails,
        onAddDocumentClicked = viewModel::addDocumentDialogVisible,
    )
    AddDocumentDialog(
        selectedContractor = state.selectedContractor,
        contractors = state.contractors,
        dialogVisible = state.dialogVisible,
        onDismissDialog = viewModel::addDocumentDialogVisible,
        onConfirmDialog = viewModel::onAddDocumentClicked,
        onSelectedContractor = viewModel::onSelectedContractor,
    )
}

@Composable
private fun DocumentsScreen(
    documents: List<Document>,
    onDocumentClicked: (Long) -> Unit,
    onAddDocumentClicked: (Boolean) -> Unit,
) {
    WhScreenContainer(
        title = stringResource(ResR.string.documents_top_bar_title),
        onClicked = { onAddDocumentClicked(true) },
    ) {
        LazyColumn {
            items(documents) { document ->
                Item(
                    document = document,
                    onDocumentClicked = onDocumentClicked,
                )
            }
        }
    }
}

@Composable
private fun Item(
    document: Document,
    onDocumentClicked: (Long) -> Unit,
) {
    WhCard(onClick = { onDocumentClicked(document.id) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            document.run {
                Text(date)
                Text(signature)
                Text(contractor.name)
            }
        }
    }
}

@Composable
private fun AddDocumentDialog(
    selectedContractor: Contractor,
    contractors: List<Contractor>,
    dialogVisible: Boolean,
    onDismissDialog: (Boolean) -> Unit,
    onSelectedContractor: (Contractor) -> Unit,
    onConfirmDialog: () -> Unit,
) {
    WhDialog(
        visible = dialogVisible,
        onDismissRequest = {},
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            ContractorSelector(
                contractors = contractors,
                selectedContractor = selectedContractor,
                onSelectedContractor = onSelectedContractor,
            )
            WhSpacer(5.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                WhDialogButton(
                    text = stringResource(ResR.string.documents_add_document_dialog_cta_button_add),
                    onClicked = onConfirmDialog,
                    weight = 1f,
                )
                WhSpacer(5.dp)
                WhDialogButton(
                    text = stringResource(ResR.string.documents_add_document_dialog_cta_button_cancel),
                    onClicked = { onDismissDialog(false) },
                    weight = 1f,
                )
            }
        }
    }
}

@Composable
private fun ContractorSelector(
    selectedContractor: Contractor,
    contractors: List<Contractor>,
    onSelectedContractor: (Contractor) -> Unit,
) {
    var dialogVisible by remember { mutableStateOf(false) }

    WhSelector(
        header = stringResource(ResR.string.documents_add_document_dialog_contractor_selector),
        selectedItem = selectedContractor.name,
        showItemList = { dialogVisible = it }
    ) {
        ContractorListDialog(
            dialogVisible = dialogVisible,
            contractors = contractors,
            onSelectedContractor = onSelectedContractor,
            onDismissRequest = { dialogVisible = false }
        )
    }
}

@Composable
private fun ContractorListDialog(
    dialogVisible: Boolean,
    contractors: List<Contractor>,
    onSelectedContractor: (Contractor) -> Unit,
    onDismissRequest: () -> Unit,
) {
    WhDialog(
        visible = dialogVisible,
        onDismissRequest = onDismissRequest,
    ) {
        WhCard {
            LazyColumn {
                items(contractors) { contractor ->
                    Column(
                        modifier = Modifier
                            .clickable {
                                onSelectedContractor(contractor)
                                onDismissRequest()
                            }
                            .padding(10.dp),
                    ) {
                        Text(text = contractor.name)
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
private fun DocumentsScreen_Preview() {
    DocumentsScreen(
        mutableListOf(fakeDocument),
        onDocumentClicked = {},
        onAddDocumentClicked = {},
    )
}

@Composable
@Preview
private fun Item_Preview() {
    Item(
        document = fakeDocument,
        onDocumentClicked = {},
    )
}
