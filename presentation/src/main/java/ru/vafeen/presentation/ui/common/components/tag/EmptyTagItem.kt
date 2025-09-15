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
import ru.vafeen.presentation.ui.theme.AppTheme

/**
 * Composable функция для отображения пустого тега.
 *
 * Отображает тег в виде закругленной карточки с префиксом "#" без текста.
 * Использует цвет serviceNames из текущей темы приложения.
 *
 * Карточка имеет:
 * - Закругление: 10dp со всех сторон
 * - Цвет фона: serviceNames из темы
 * - Внутренние отступы: 6dp по горизонтали, 4dp по вертикали
 * - Размер шрифта текста: 10sp
 */
@Composable
internal fun EmptyTagItem() {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.serviceNames)
    ) {
        Text(
            "# ",
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            fontSize = 10.sp
        )
    }
}
