package ru.vafeen.presentation.ui.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vafeen.domain.models.Advertisement
import ru.vafeen.presentation.ui.common.converters.toComposeColor


@Composable
internal fun Advertisement.Tag.TagItem() {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = this@TagItem.color.toComposeColor())
    ) {
        Text(
            "# ${this@TagItem.name}",
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
        )
    }
}