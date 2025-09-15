package ru.vafeen.presentation.ui.common.components.filters

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


@HiltViewModel
internal class FiltersViewModel @AssistedInject constructor(
    @Assisted private val initialState: FiltersState,
    @Assisted private val onDismissRequest: () -> Unit,
    @Assisted private val applyFilters: (FiltersState) -> Unit,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()
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

    private fun addCurrentTagToTags() {
        val state = _state.value
        val currentTags = state.tags
//        val newTag = Advertisement.Tag()
//        _state.update { it.copy(tags = it.tags.plus(it.currentTag), ) }
    }

    private fun setGender(gender: Gender) = _state.update { it.copy(gender = gender) }
    private fun setCurrentTag(currentTag: String) =
        _state.update { it.copy(currentTag = currentTag) }

    private fun setAgeFrom(ageFrom: Int?) = _state.update { it.copy(ageFrom = ageFrom) }
    private fun setAgeTo(ageTo: Int?) = _state.update { it.copy(ageTo = ageTo) }
    private fun onDismissRequest() = onDismissRequest.invoke()
    private fun applyFilters() = applyFilters.invoke(_state.value)
    private fun clearFilters() {
        applyFilters.invoke(FiltersState())
        onDismissRequest()
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted initialState: FiltersState,
            @Assisted onDismissRequest: () -> Unit,
            @Assisted applyFilters: (FiltersState) -> Unit,
        ): FiltersViewModel
    }
}