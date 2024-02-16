package com.example.presentation.screen.goodsDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.Goods
import com.example.presentation.component.EditGoodsDialog
import com.example.presentation.component.WhCard
import com.example.presentation.component.WhScreenContainer

@Composable
internal fun GoodsDetailsScreen(
    navigateBack: () -> Unit,
) {
    val viewModel: GoodsDetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.event.collect { event ->
            when (event) {
                GoodsDetailsViewModel.Event.NavigateBack -> navigateBack()
            }
        }
    }

    GoodsDetailsScreen(
        goods = state.goods,
        onDeleteClicked = viewModel::onDeleteGoodsClicked,
        onEditClicked = viewModel::editGoodsDialogVisible,
    )
    EditGoodsDialog(
        visible = state.editGoodsDialogVisible,
        textFieldValueForName = state.textFieldValueForName,
        textFieldValueForAmount = state.textFieldValueForAmount,
        selectedUnitOfMeasure = state.selectedUnitOfMeasure,
        onConfirmDialog = viewModel::onEditGoodsClicked,
        onSelectedUnitOfMeasure = viewModel::onSelectedUnitOfMeasure,
        onDismissDialog = viewModel::editGoodsDialogVisible,
        onTextValueChange = viewModel::onTextValueChange,
    )
}

@Composable
private fun GoodsDetailsScreen(
    goods: Goods,
    onDeleteClicked: () -> Unit,
    onEditClicked: (Boolean) -> Unit,
) {
    WhScreenContainer(
        title = "Szczegóły pozycji",
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                DetailsRow(
                    title = "Nazwa",
                    content = goods.name,
                )
                DetailsRow(
                    title = goods.unitOfMeasure.name,
                    content = goods.amount.toString(),
                )
            }
            ClickableCardsRow(
                onEditClicked = onEditClicked,
                onDeleteClicked = onDeleteClicked,
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
private fun ClickableCardsRow(
    onDeleteClicked: () -> Unit,
    onEditClicked: (Boolean) -> Unit,
) {
    Row(modifier = Modifier) {
        ClickableCard(text = "EDYTUJ") { onEditClicked(true) }
        ClickableCard(text = "USUN", onDeleteClicked)
    }
}

@Composable
private fun RowScope.ClickableCard(
    text: String,
    onClicked: () -> Unit,
) {
    WhCard(
        modifier = Modifier.weight(1f),
        onClick = onClicked,
    ) {
        Box(
            modifier = Modifier.padding(50.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
@Preview
private fun GoodsDetailsScreen_Preview() {
    GoodsDetailsScreen(
        navigateBack = {},
    )
}

@Composable
@Preview
private fun ClickableCard_Preview() {
    Row {
        ClickableCard(
            text = "LoremIpsum",
            onClicked = {},
        )
    }
}
