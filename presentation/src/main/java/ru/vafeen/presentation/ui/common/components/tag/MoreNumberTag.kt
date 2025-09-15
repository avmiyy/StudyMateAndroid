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
 * Composable функция для отображения тега с количеством дополнительных элементов.
 *
 * Отображает тег в виде закругленной карточки с текстом вида "+N",
 * где N — количество скрытых элементов.
 *
 * @param numberMore Количество скрытых элементов, отображаемое в теге.
 *
 * Карточка имеет:
 * - Закругление: 10dp со всех сторон
 * - Цвет фона: serviceNames из текущей темы
 * - Внутренние отступы: 6dp по горизонтали, 4dp по вертикали
 * - Размер шрифта текста: 10sp
 */
@Composable
internal fun MoreNumberTag(numberMore: Int) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.serviceNames)
    ) {
        Text(
            "+$numberMore",
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            fontSize = 10.sp
        )
    }
}