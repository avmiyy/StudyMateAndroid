package ru.vafeen.presentation.ui.screen.advertisements

import ru.vafeen.domain.models.Advertisement

internal data class AdvertisementsState(
    val advertisements: List<Advertisement> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)