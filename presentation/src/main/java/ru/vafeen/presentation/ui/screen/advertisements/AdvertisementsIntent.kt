package ru.vafeen.presentation.ui.screen.advertisements

/**
 * Закрытое (sealed) множество интентов, описывающих пользовательские действия
 * и события для экрана объявлений.
 */
internal sealed class AdvertisementsIntent {

    /**
     * Интент для установки видимости панели фильтров в нижнем листе.
     *
     * @property isVisible Флаг видимости панели фильтров (true - видима, false - скрыта)
     */
    data class SetFiltersBottomSheetVisible(val isVisible: Boolean) : AdvertisementsIntent()

    /**
     * Интент для установки новой поисковой строки запроса.
     *
     * @property searchRequest Новая строка поискового запроса.
     */
    data class SetSearchRequest(val searchRequest: String) : AdvertisementsIntent()
}
