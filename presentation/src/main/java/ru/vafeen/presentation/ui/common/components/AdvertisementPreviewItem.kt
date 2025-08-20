package ru.vafeen.presentation.ui.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vafeen.domain.models.Advertisement

/**
 * Composable функция для отображения превью объявления в карточке.
 *
 * Располагает ID слева и далее заголовок с именем автора в колонке.
 */
@Composable
internal fun Advertisement.AdvertisementPreviewItem() {
    Card(modifier = Modifier.padding(5.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("$id")
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                Text(text = title)
                Text(text = authorName)
            }
        }
    }
}
