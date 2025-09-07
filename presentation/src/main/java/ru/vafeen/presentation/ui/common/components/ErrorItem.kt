package ru.vafeen.presentation.ui.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vafeen.presentation.ui.theme.AppTheme

/**
 * Отображает элемент с сообщением об ошибке и кнопкой повторной попытки.
 *
 * @param message Текст сообщения об ошибке для отображения.
 * @param modifier Модификатор для настройки внешнего вида и поведения компонента.
 * @param onClickRetry Колбэк, вызываемый при нажатии на кнопку "Повторить".
 */
@Composable
internal fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = AppTheme.colors.error)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onClickRetry) {
            Text(text = "Retry")
        }
    }
}
