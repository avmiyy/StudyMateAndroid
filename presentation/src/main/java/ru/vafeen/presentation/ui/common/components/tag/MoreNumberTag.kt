package ru.vafeen.presentation.ui.common.components.tag

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
internal fun MoreNumberTag(numberMore: Int) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Text(
            "+$numberMore",
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            fontSize = 10.sp
        )
    }
}