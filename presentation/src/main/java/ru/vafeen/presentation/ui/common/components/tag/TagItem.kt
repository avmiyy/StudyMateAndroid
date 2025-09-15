package ru.vafeen.presentation.ui.common.components.tag

import androidx.compose.foundation.border
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
import ru.vafeen.domain.models.Advertisement
import ru.vafeen.presentation.ui.common.converters.toComposeColor
import ru.vafeen.presentation.ui.theme.AppTheme

/**
 * Composable функция для отображения отдельного тега в виде карточки.
 *
 * Отображает тег в виде закругленной карточки с хештегом и названием тега.
 * Цвет карточки соответствует цвету тега.
 * Если цвет тега отсутствует (прозрачный или Smart logic), то применяется обводка с цветом текста темы.
 *
 * Карточка тега имеет:
 * - Закругление: 10dp со всех сторон
 * - Цвет фона: из цвета тега, конвертированный в Compose Color
 * - Внутренние отступы: 6dp по горизонтали, 4dp по вертикали
 * - Обводку, если цвет прозрачный (цвет не задан)
 */
@Composable
internal fun Advertisement.Tag.TagItem() {
    val composeColor = this@TagItem.color.toComposeColor()
    val borderModifier = if (composeColor == Color.Transparent) {
        Modifier.border(1.dp, AppTheme.colors.text, RoundedCornerShape(10.dp))
    } else {
        Modifier
    }

    Card(
        modifier = borderModifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = if (composeColor == Color.Transparent) Color.Unspecified else composeColor)
    ) {
        Text(
            "# ${this@TagItem.name}",
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            fontSize = 10.sp
        )
    }
}
