package ru.vafeen.presentation.ui.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vafeen.domain.models.Advertisement

@Composable
internal fun AdvertisementPreviewItem(advertisement: Advertisement) {
    Card(modifier = Modifier.padding(5.dp)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Text(text = advertisement.title)
            Text(text = advertisement.authorName)
        }
    }
}