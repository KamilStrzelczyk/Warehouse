package com.example.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.component.WHCard
import com.example.resources.R as ResR

private const val WEIGHT_FOR_CARD = 1f

@Composable
fun HomeScreen(
    onFirstButtonClicked: () -> Unit,
    onSecondButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val modifierForCardWithWeight = Modifier.weight(WEIGHT_FOR_CARD)
        ClickableCard(
            modifier = modifierForCardWithWeight,
            text = stringResource(ResR.string.home_document_card),
            onClicked = onFirstButtonClicked,
        )
        ClickableCard(
            modifier = modifierForCardWithWeight,
            text = stringResource(ResR.string.home_contractors_card),
            onClicked = onSecondButtonClicked,
        )
    }
}

@Composable
private fun ClickableCard(
    modifier: Modifier,
    text: String,
    onClicked: () -> Unit,
) {
    WHCard(
        modifier = modifier.fillMaxSize(),
        onClicked = onClicked,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text)
        }
    }
}

@Composable
@Preview
private fun HomeScreen_Preview(function: () -> Unit) {
    HomeScreen(
        onFirstButtonClicked = function,
        onSecondButtonClicked = function,
    )
}
