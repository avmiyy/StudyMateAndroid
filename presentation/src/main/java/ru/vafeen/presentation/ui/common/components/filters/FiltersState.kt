package ru.vafeen.presentation.ui.common.components.filters

import ru.vafeen.domain.models.Advertisement

/**
 * Состояние фильтров для экрана фильтрации объявлений.
 *
 * @property city Название города для фильтрации, или null если не задано.
 * @property gender Пол для фильтрации, по умолчанию [Gender.Every].
 * @property ageFrom Минимальный возраст для фильтрации, или null.
 * @property ageTo Максимальный возраст для фильтрации, или null.
 * @property tags Список выбранных тегов для фильтрации.
 * @property currentTag Текущий вводимый тег, используемый для добавления в список.
 */
internal data class FiltersState(
    val city: String? = null,
    val gender: Gender = Gender.Every,
    val ageFrom: Int? = null,
    val ageTo: Int? = null,
    val tags: List<Advertisement.Tag> = listOf(),
    val currentTag: String = "",
)
