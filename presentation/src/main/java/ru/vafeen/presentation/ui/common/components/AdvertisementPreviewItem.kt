package ru.vafeen.presentation.ui.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vafeen.domain.models.Advertisement
import ru.vafeen.presentation.ui.common.converters.toComposeColor
import ru.vafeen.presentation.ui.theme.AppTheme
import ru.vafeen.presentation.ui.theme.ServiceNamesColor

/**
 * Composable функция для отображения превью объявления в карточке.
 *
 * Располагает ID слева и далее заголовок с именем автора в колонке.
 */
@Composable
internal fun Advertisement.AdvertisementPreviewItem() {
    Card(
        modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.backgroundText
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = this@AdvertisementPreviewItem.bgColor?.toComposeColor()
                    ?: Color.Transparent
            ),
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomEnd = 0.dp,
                bottomStart = 20.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    // TODO(" тут место для картинки")
                    Text(this@AdvertisementPreviewItem.authorName, fontSize = 14.sp)
                }
            }
        }
        if (this@AdvertisementPreviewItem.tags.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Теги",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W400,
                    color = ServiceNamesColor
                )
                Spacer(modifier = Modifier.height(4.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    this@AdvertisementPreviewItem.tags.forEach { tag ->
                        tag.TagItem()
                    }
                }
            }
        }
    }
}
