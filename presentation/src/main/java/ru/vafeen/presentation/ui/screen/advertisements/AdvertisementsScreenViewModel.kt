package ru.vafeen.presentation.ui.screen.advertisements

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vafeen.domain.models.Advertisement
import ru.vafeen.domain.network.repository.AdvertisementsRemoteRepository
import ru.vafeen.domain.network.result.ResponseResult
import javax.inject.Inject

@HiltViewModel
internal class AdvertisementsScreenViewModel @Inject constructor(
    private val advertisementsRemoteRepository: AdvertisementsRemoteRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AdvertisementsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        _state.update { it.copy(isLoading = true, error = null) }
        delay(5000)
        when (val result = advertisementsRemoteRepository.getAnnouncements()) {
            is ResponseResult.Success<List<Advertisement>> -> _state.update {
                it.copy(
                    isLoading = false,
                    advertisements = result.data,
                    error = null
                )
            }

            is ResponseResult.Error -> _state.update {
                it.copy(
                    isLoading = false,
                    error = result.stacktrace
                ).also {
                    Log.e("error", result.stacktrace)
                }
            }
        }
    }
}