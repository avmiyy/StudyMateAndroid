package ru.vafeen.presentation.ui.screen.advertisements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vafeen.domain.network.repository.AdvertisementsRemoteRepository
import javax.inject.Inject

@HiltViewModel
internal class AdvertisementsScreenViewModel @Inject constructor(
    private val advertisementsRemoteRepository: AdvertisementsRemoteRepository
) : ViewModel() {
    val pagedAdvertisementsFlow =
        advertisementsRemoteRepository
            .getPagedAnnouncements()
            .cachedIn(viewModelScope)

}