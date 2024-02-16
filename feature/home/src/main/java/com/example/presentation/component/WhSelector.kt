package com.example.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun WhSelector(
    header: String,
    selectedItem: String,
    showItemList: (Boolean) -> Unit,
    itemList: @Composable () -> Unit,
) {
    Column {
        Text(text = header)
        WhSpacer(5.dp)
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(5.dp),
                )
                .fillMaxWidth()
                .clickable { showItemList(true) }
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = selectedItem)
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
            )
        }
        itemList()
    }
}
