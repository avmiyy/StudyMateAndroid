package ru.vafeen.presentation.ui.common.converters

import android.graphics.Color as AndroidColor
import androidx.compose.ui.graphics.Color as ComposeColor

fun AndroidColor.toComposeColor(): ComposeColor = ComposeColor(
    red = this.red(),
    green = this.green(),
    blue = this.blue(),
    alpha = this.alpha()
)