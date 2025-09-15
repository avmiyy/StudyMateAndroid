package ru.vafeen.presentation.ui.screen.advertisements

import androidx.compose.ui.focus.FocusRequester

/**
 * Состояние экрана объявлений, содержащее информацию о видимости фильтров,
 * строке поиска и объекте управления фокусом для элементов фильтрации.
 *
 * @property isFiltersVisible Флаг видимости панели фильтров (по умолчанию true)
 * @property searchRequest Текущая строка поискового запроса (по умолчанию пустая)
 * @property filtersFocusRequester Объект [FocusRequester] для управления фокусом на фильтрах
 */
internal data class AdvertisementsState(
    val isFiltersVisible: Boolean = true,
    val searchRequest: String = "",
    val filtersFocusRequester: FocusRequester = FocusRequester()
)
