package com.example.documents.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.component.WHCard
import com.example.component.WHScreenContainer
import com.example.resources.R as ResR

@Composable
fun DocumentsScreen() {
    val viewModel: DocumentsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    DocumentsScreen(state.documents)
}

@Composable
private fun DocumentsScreen(documents: List<Document>) {
    WHScreenContainer(
        title = stringResource(ResR.string.documents_top_bar_title),
        onClicked = {},
    ) {
        LazyColumn {
            items(documents) { document ->
                Item(document)
            }
        }
    }
}

@Composable
private fun Item(
    document: Document,
) {
    WHCard(onClicked = {}) {
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
                Text(contractor)
            }
        }
    }
}

data class Document(
    val date: String,
    val signature: String,
    val contractor: String,
)

@Composable
@Preview
private fun DocumentsScreen_Preview() {
    DocumentsScreen(
        mutableListOf(
            Document(
                date = "12/12/2034",
                signature = "AAAA",
                contractor = "Contractor",
            )
        )
    )
}

@Composable
@Preview
private fun Item_Preview() {
    Item(
        Document(
            date = "12/12/2034",
            signature = "AAAA",
            contractor = "Contractor",
        )
    )
}
