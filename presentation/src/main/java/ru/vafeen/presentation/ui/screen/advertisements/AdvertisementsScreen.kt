package ru.vafeen.presentation.ui.screen.advertisements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.vafeen.presentation.ui.common.components.AdvertisementPreviewItem
import ru.vafeen.presentation.ui.common.components.Loading

@Composable
internal fun AdvertisementsScreen() {
    val viewModel = hiltViewModel<AdvertisementsScreenViewModel>()
    val state by viewModel.state.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> Loading()
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.advertisements) { advertisement ->
                        AdvertisementPreviewItem(advertisement = advertisement)
                    }
                }
            }
        }
    }
}