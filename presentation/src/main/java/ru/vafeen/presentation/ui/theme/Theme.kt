package ru.vafeen.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Класс, описывающий цветовую палитру приложения.
 *
 * @property mainColor Основной цвет, используемый в приложении.
 * @property background Цвет фона интерфейса.
 * @property text Цвет текста.
 * @property buttonColor Цвет кнопок.
 * @property error Цвет, указывающий на ошибки.
 */
internal data class AppThemeColors(
    val mainColor: Color,
    val background: Color,
    val text: Color,
    val backgroundText: Color,
    val buttonColor: Color,
    val error: Color,
)

/**
 * Светлая палитра цветов для темы приложения.
 */
private val basePalette = AppThemeColors(
    mainColor = Color(0xFFECEA0E),
    background = Background,
    text = Color.Black,
    backgroundText = Color.White,
    error = Color.Red,
    buttonColor = Color(0xFFF9F9F9)
)

/**
 * Темная палитра цветов для темы приложения, основанная на [basePalette] c переопределениями.
 */
private val baseDarkPalette = basePalette.copy(
    background = Color.Black,
    text = Color.White,
    backgroundText = Color.Black,
    buttonColor = Color(0xFF2D2D31)
)

/**
 * Компонент-тема, применяющий к подсоставному контенту основную цветовую схему.
 *
 * Выбирает светлую или темную палитру в зависимости от параметра [darkTheme],
 * который по умолчанию соответствует системной настройке.
 *
 * @param darkTheme Использовать ли темную тему.
 * @param content Составной контент, к которому применяется тема.
 */
@Composable
internal fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        baseDarkPalette
    } else {
        basePalette
    }

    CompositionLocalProvider(
        LocalColors provides colors,
        content = content
    )
}

/**
 * Объект для доступа к текущим цветам темы приложения из композиции.
 */
internal object AppTheme {
    /**
     * Текущая палитра цветов темы.
     */
    val colors: AppThemeColors
        @Composable
        get() = LocalColors.current
}

/**
 * CompositionLocal для хранения текущей палитры цветов [AppThemeColors].
 *
 * Вызывает ошибку, если используется вне компонента [MainTheme].
 */
private val LocalColors = staticCompositionLocalOf<AppThemeColors> {
    error("Composition error: AppThemeColors not provided")
}
