package ru.vafeen.presentation.ui.common.components.filters

import ru.vafeen.domain.models.Advertisement


internal data class FiltersState(
    val city: String? = null,
    val gender: Gender = Gender.Every,
    val ageFrom: Int? = null,
    val ageTo: Int? = null,
    val tags: List<Advertisement.Tag> = listOf(),
)