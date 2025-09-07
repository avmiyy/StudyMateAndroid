package ru.vafeen.presentation.ui.screen.advertisements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vafeen.domain.network.repository.AdvertisementsRemoteRepository
import javax.inject.Inject

/**
 * ViewModel для экрана объявлений.
 *
 * Обеспечивает получение постраничного потока объявлений из удаленного репозитория
 * и кэширование потока в пределах жизненного цикла ViewModel.
 *
 * @property advertisementsRemoteRepository Репозиторий для работы с удалёнными объявлениями.
 */
@HiltViewModel
internal class AdvertisementsScreenViewModel @Inject constructor(
    private val advertisementsRemoteRepository: AdvertisementsRemoteRepository
) : ViewModel() {

    /**
     * Поток постраничных данных объявлений, кэшируемый в ViewModelScope.
     */
    val pagedAdvertisementsFlow =
        advertisementsRemoteRepository
            .getPagedAnnouncements()
            .cachedIn(viewModelScope)
}
