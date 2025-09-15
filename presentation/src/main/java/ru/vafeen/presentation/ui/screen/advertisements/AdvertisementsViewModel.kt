package ru.vafeen.presentation.ui.screen.advertisements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
internal class AdvertisementsViewModel @Inject constructor(
    private val advertisementsRemoteRepository: AdvertisementsRemoteRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AdvertisementsState())

    /**
     * Наблюдаемый поток состояния экрана объявлений.
     */
    val state = _state.asStateFlow()

    /**
     * Поток постраничных данных объявлений, кэшируемый в [viewModelScope].
     */
    val pagedAdvertisementsFlow =
        advertisementsRemoteRepository
            .getPagedAnnouncements()
            .cachedIn(viewModelScope)

    /**
     * Обрабатывает интенты пользовательских действий с экрана.
     *
     * @param intent Интент действия пользователя на экране объявлений.
     */
    fun handleIntent(intent: AdvertisementsIntent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (intent) {
                is AdvertisementsIntent.SetFiltersBottomSheetVisible ->
                    setFiltersBottomSheetVisible(intent.isVisible)

                is AdvertisementsIntent.SetSearchRequest ->
                    setSearchRequest(intent.searchRequest)
            }
        }
    }

    /**
     * Обновляет флаг видимости панели фильтров.
     *
     * @param isVisible Новый флаг видимости фильтров.
     */
    private fun setFiltersBottomSheetVisible(isVisible: Boolean) =
        _state.update { it.copy(isFiltersVisible = isVisible) }

    /**
     * Обновляет строку поискового запроса.
     *
     * @param searchRequest Новая поисковая строка.
     */
    private fun setSearchRequest(searchRequest: String) =
        _state.update { it.copy(searchRequest = searchRequest) }
}
