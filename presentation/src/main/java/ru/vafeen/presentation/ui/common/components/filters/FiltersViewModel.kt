package ru.vafeen.presentation.ui.common.components.filters

import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vafeen.domain.models.Advertisement

/**
 * ViewModel для управления состоянием фильтров в UI.
 *
 * Обрабатывает пользовательские намерения (интенты),
 * изменяет состояние фильтров и управляет их применением или сбросом.
 *
 * @property initialState Исходное состояние фильтров.
 * @property onDismissRequest Колбэк, вызываемый при запросе закрытия фильтров.
 * @property applyFilters Колбэк, вызываемый для применения текущего состояния фильтров.
 */
@HiltViewModel(assistedFactory = FiltersViewModel.Factory::class)
internal class FiltersViewModel @AssistedInject constructor(
    @Assisted private val initialState: FiltersState,
    @Assisted private val onDismissRequest: () -> Unit,
    @Assisted private val applyFilters: (FiltersState) -> Unit,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)

    /**
     * Наблюдаемый [kotlinx.coroutines.flow.StateFlow] текущего состояния фильтров.
     */
    val state = _state.asStateFlow()

    /**
     * Обрабатывает интенты, поступающие из UI, вызывая соответствующие действия.
     *
     * @param intent Интент пользователя для управления фильтрами.
     */
    fun handleIntent(intent: FiltersIntent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (intent) {
                FiltersIntent.ApplyFilters -> applyFilters()
                FiltersIntent.ClearFilters -> clearFilters()
                FiltersIntent.OnDismissRequest -> onDismissRequest()
                is FiltersIntent.SetAgeFrom -> setAgeFrom(intent.ageFrom)
                is FiltersIntent.SetAgeTo -> setAgeTo(intent.ageTo)
                is FiltersIntent.SetCurrentTag -> setCurrentTag(intent.currentTag)
                is FiltersIntent.SetGender -> setGender(intent.gender)
                FiltersIntent.AddCurrentTagToTags -> addCurrentTagToTags()
            }
        }
    }

    /**
     * Добавляет текущий тег в список выбранных тегов.
     */
    private fun addCurrentTagToTags() {
        val currentTag = _state.value.currentTag
        if (currentTag.isNotBlank()) {
            val newTag =
                Advertisement.Tag(id = 0, name = currentTag, color = Color.valueOf(Color.WHITE))
            _state.update { it.copy(tags = it.tags.plus(newTag), currentTag = "") }
        }
    }

    /**
     * Устанавливает фильтр пола.
     *
     * @param gender Значение пола.
     */
    private fun setGender(gender: Gender) = _state.update { it.copy(gender = gender) }

    /**
     * Устанавливает текущий тег для фильтра.
     *
     * @param currentTag Название текущего тега.
     */
    private fun setCurrentTag(currentTag: String) =
        _state.update { it.copy(currentTag = currentTag) }

    /**
     * Устанавливает минимальный возраст для фильтра.
     *
     * @param ageFrom Минимальный возраст, или null.
     */
    private fun setAgeFrom(ageFrom: Int?) = _state.update { it.copy(ageFrom = ageFrom) }

    /**
     * Устанавливает максимальный возраст для фильтра.
     *
     * @param ageTo Максимальный возраст, или null.
     */
    private fun setAgeTo(ageTo: Int?) = _state.update { it.copy(ageTo = ageTo) }

    /**
     * Обрабатывает запрос закрытия фильтров.
     */
    private fun onDismissRequest() = onDismissRequest.invoke()

    /**
     * Применяет текущие фильтры, вызывая соответствующий колбэк.
     */
    private fun applyFilters() = applyFilters.invoke(_state.value)

    /**
     * Сбрасывает все фильтры в исходное состояние и закрывает экран фильтров.
     */
    private fun clearFilters() {
        applyFilters.invoke(FiltersState())
        onDismissRequest()
    }

    /**
     * Фабрика для создания экземпляров [FiltersViewModel] с параметрами.
     */
    @AssistedFactory
    interface Factory {
        /**
         * Создаёт [FiltersViewModel].
         *
         * @param initialState Начальное состояние фильтров.
         * @param onDismissRequest Колбэк закрытия.
         * @param applyFilters Колбэк применения фильтров.
         * @return Новый экземпляр [FiltersViewModel].
         */
        fun create(
            @Assisted initialState: FiltersState,
            @Assisted onDismissRequest: () -> Unit,
            @Assisted applyFilters: (FiltersState) -> Unit,
        ): FiltersViewModel
    }
}
