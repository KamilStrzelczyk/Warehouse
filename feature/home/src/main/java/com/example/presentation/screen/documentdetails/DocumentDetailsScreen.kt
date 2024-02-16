package com.example.presentation.screen.documentdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.Document
import com.example.domain.model.Goods
import com.example.presentation.component.AddGoodsDialog
import com.example.presentation.component.WhCard
import com.example.presentation.component.WhScreenContainer
import com.example.presentation.component.WhSpacer
import com.example.presentation.component.fakeDocument
import com.example.presentation.component.fakeGoods

@Composable
internal fun DocumentDetailsScreen(
    navigateToGoodsDetails: (goodsId: Long, documentId: Long) -> Unit,
) {
    val viewModel: DocumentDetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    DocumentDetailsScreen(
        document = state.document,
        goods = state.goods,
        onClicked = { goodsId -> navigateToGoodsDetails(goodsId, state.document.id) },
        onAddGoodsClicked = viewModel::addGoodsDialogVisible,
    )
    AddGoodsDialog(
        selectedUnitOfMeasure = state.selectedUnitOfMeasure,
        textFieldValueForName = state.textFieldValueForName,
        textFieldValueForAmount = state.textFieldValueForAmount,
        visible = state.addGoodsDialogVisible,
        onConfirmDialog = viewModel::onAddGoodsClicked,
        onTextValueChange = viewModel::onTextValueChange,
        onSelectedUnitOfMeasure = viewModel::onSelectedUnitOfMeasure,
        onDismissDialog = viewModel::addGoodsDialogVisible,
    )
}

@Composable
internal fun DocumentDetailsScreen(
    document: Document,
    goods: List<Goods>,
    onClicked: (goodsId: Long) -> Unit,
    onAddGoodsClicked: (Boolean) -> Unit,
) {
    WhScreenContainer(
        title = { Header(document) },
        onClicked = { onAddGoodsClicked(true) },
    ) {
        LazyColumn {
            itemsIndexed(goods) { index, it ->
                Item(
                    index = index + 1,
                    goods = it,
                    onClicked = { onClicked(it.id) },
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
                        Text(text = amount.toString())
                    }
                }
            }
        }
    }
}

@Composable
private fun Header(document: Document) {
    document.run {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderText(date)
            HeaderText(signature)
            HeaderText(contractorName)
        }
    }
}

@Composable
private fun HeaderText(title: String) {
    Text(
        text = title,
        fontSize = 13.sp,
    )
}

@Composable
@Preview
private fun DocumentDetailsScreen_Preview() {
    DocumentDetailsScreen(
        document = fakeDocument,
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
