package ru.vafeen.presentation.ui.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vafeen.presentation.ui.theme.AppTheme

@Composable
internal fun Loading() {
    CircularProgressIndicator(
        modifier = Modifier.size(50.dp),
        color = AppTheme.colors.mainColor
    )
}