package ru.vafeen.presentation.ui.screen.advertisements

internal sealed class AdvertisementsIntent {
    data class SetFiltersBottomSheetVisible(val isVisible: Boolean) : AdvertisementsIntent()
    data class SetSearchRequest(val searchRequest: String) : AdvertisementsIntent()
}