package ru.vafeen.presentation.ui.screen.advertisements

import androidx.compose.ui.focus.FocusRequester

internal data class AdvertisementsState(
    val isFiltersVisible: Boolean = true,
    val searchRequest: String = "",
    val filtersFocusRequester: FocusRequester = FocusRequester()
)