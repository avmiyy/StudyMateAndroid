package ru.vafeen.presentation.ui.common.components.tag

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vafeen.domain.models.Advertisement
import ru.vafeen.presentation.ui.common.converters.toComposeColor

/**
 * Composable функция для отображения отдельного тега в виде карточки.
 *
 * Отображает тег в виде закругленной карточки с хештегом и названием тега.
 * Цвет карточки соответствует цвету тега.
 *
 * @property name Название тега, отображается с префиксом "#"
 * @property color Цвет фона карточки тега, конвертируется в Compose Color
 *
 * Карточка тега имеет:
 * - Закругление: 10dp со всех сторон
 * - Цвет фона: из color тега, конвертированный в Compose Color
 * - Внутренние отступы: 6dp по горизонтали, 4dp по вертикали
 */
@Composable
internal fun Advertisement.Tag.TagItem() {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = this@TagItem.color.toComposeColor())
    ) {
        Text(
            "# ${this@TagItem.name}",
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            fontSize = 10.sp
        )
    }
}