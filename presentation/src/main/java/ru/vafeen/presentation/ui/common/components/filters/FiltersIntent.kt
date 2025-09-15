package ru.vafeen.presentation.ui.common.components.filters

/**
 * Класс, описывающий возможные намерения (интенты) для управления фильтрами.
 */
internal sealed class FiltersIntent {

    /**
     * Устанавливает пол для фильтрации.
     *
     * @property gender Значение пола.
     */
    data class SetGender(val gender: Gender) : FiltersIntent()

    /**
     * Устанавливает минимальный возраст для фильтрации.
     *
     * @property ageFrom Минимальный возраст, или null.
     */
    data class SetAgeFrom(val ageFrom: Int?) : FiltersIntent()

    /**
     * Устанавливает максимальный возраст для фильтрации.
     *
     * @property ageTo Максимальный возраст, или null.
     */
    data class SetAgeTo(val ageTo: Int?) : FiltersIntent()

    /**
     * Устанавливает текущий вводимый тег.
     *
     * @property currentTag Текущий тег для фильтра.
     */
    data class SetCurrentTag(val currentTag: String) : FiltersIntent()

    /**
     * Запрос на закрытие экрана фильтров.
     */
    data object OnDismissRequest : FiltersIntent()

    /**
     * Запрос на применение текущих фильтров.
     */
    data object ApplyFilters : FiltersIntent()

    /**
     * Запрос на очистку всех фильтров.
     */
    data object ClearFilters : FiltersIntent()

    /**
     * Запрос на добавление текущего тега в список выбранных тегов.
     */
    data object AddCurrentTagToTags : FiltersIntent()
}
