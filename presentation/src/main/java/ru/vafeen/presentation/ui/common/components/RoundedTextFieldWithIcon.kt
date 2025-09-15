package ru.vafeen.presentation.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Текстовое поле с закругленными углами и опциональной иконкой.
 *
 * Предоставляет кастомное текстовое поле с белым фоном, закругленными углами 20dp
 * и возможностью добавления иконки в начале.
 *
 * @param modifier Модификатор для настройки внешнего вида и поведения текстового поля
 * @param value Текущее значение текстового поля
 * @param onValueChange Функция обратного вызова при изменении значения текстового поля
 * @param placeholder Текст-подсказка, отображаемый когда поле пустое
 * @param icon Опциональная иконка для отображения в начале текстового поля
 */
@Composable
fun RoundedTextFieldWithIcon(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Поиск...",
    focusRequester: FocusRequester? = null,
    icon: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Иконка
            icon?.invoke()

            if (icon != null) {
                Spacer(modifier = Modifier.width(12.dp))
            }

            // Текстовое поле
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .let {
                        it.focusRequester(focusRequester = focusRequester ?: return@let it)
                    }
            )
        }
    }
}
