package com.example.contractors.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
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
fun ContractorsScreen() {
    val viewModel: ContractorsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    ContractorsScreen(
        contractors = state.contractors,
        onAddClicked = {},
    )
}

@Composable
private fun ContractorsScreen(
    contractors: List<String>,
    onAddClicked: () -> Unit,
) {
    WHScreenContainer(
        title = stringResource(ResR.string.contractors_top_bar_title),
        onClicked = { onAddClicked() },
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
    contractor: String,
    onMoreClicked: () -> Unit,
) {
    WHCard(onClicked = {}) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(contractor)
            Icon(
                Icons.Default.MoreVert,
                contentDescription = null,
                modifier = Modifier.clickable { onMoreClicked() },
            )
        }
    }
}

@Composable
@Preview
private fun ContractorsScreen_Preview() {
    ContractorsScreen(
        contractors = mutableListOf("Contractor"),
        onAddClicked = {},
    )
}

@Composable
@Preview
private fun Item_Preview() {
    Item(
        contractor = "Contractor",
        onMoreClicked = {},
    )
}
