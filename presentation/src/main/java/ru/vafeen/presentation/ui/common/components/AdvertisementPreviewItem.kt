package ru.vafeen.presentation.ui.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vafeen.domain.models.Advertisement
import ru.vafeen.presentation.ui.common.components.tag.EmptyTagItem
import ru.vafeen.presentation.ui.common.components.tag.MoreNumberTag
import ru.vafeen.presentation.ui.common.components.tag.TagItem
import ru.vafeen.presentation.ui.common.converters.toComposeColor
import ru.vafeen.presentation.ui.theme.AppTheme

/**
 * Composable функция для отображения превью объявления в виде карточки.
 *
 * Отображает информацию об объявлении: заголовок, имя автора и теги.
 * Поддерживает нажатие на всю карточку.
 *
 * @receiver Объект объявления [Advertisement], для отображения данных.
 * @param onClick Обратный вызов, вызываемый при нажатии на карточку.
 */
@Composable
internal fun Advertisement.AdvertisementPreviewItem(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.backgroundText)
    ) {
        Column(modifier = Modifier.clickable(onClick = onClick)) {
            Card(
                modifier = Modifier.fillMaxWidth(),
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
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        // Можно добавить изображение автора сюда
                        Text(
                            text = this@AdvertisementPreviewItem.authorName,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Теги",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W400,
                    color = AppTheme.colors.serviceNames
                )
                Spacer(modifier = Modifier.height(4.dp))
                this@AdvertisementPreviewItem.tags.TagRow()
            }
        }
    }
}

/**
 * Отображает строку тегов объявления.
 *
 * Показывает до 3 тегов, если тегов больше - отображает счетчик оставшихся.
 * Если тегов нет - отображает пустой элемент.
 *
 * @receiver Список тегов объявления.
 * @param modifier Модификатор для строки.
 */
@Composable
internal fun List<Advertisement.Tag>.TagRow(
    modifier: Modifier = Modifier,
) {
    if (this.isNotEmpty()) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            // Берем первые 3 тега
            this@TagRow.take(3).forEach { tag ->
                tag.TagItem()
            }

            // Если тегов больше 3, показываем счетчик оставшихся
            if (this@TagRow.size > 3) {
                val remainingCount = this@TagRow.size - 3
                MoreNumberTag(numberMore = remainingCount)
            }
        }
    } else {
        EmptyTagItem()
    }
}
