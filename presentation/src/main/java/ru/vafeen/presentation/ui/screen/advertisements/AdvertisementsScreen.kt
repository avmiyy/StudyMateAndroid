package ru.vafeen.presentation.ui.screen.advertisements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import ru.vafeen.presentation.ui.common.components.AdvertisementPreviewItem
import ru.vafeen.presentation.ui.common.components.ErrorItem
import ru.vafeen.presentation.ui.common.components.LoadingItem

/**
 * Экран для отображения списка объявлений с поддержкой пагинации.
 *
 * Загружает объявления из ViewModel, отображает список с состояниями загрузки и ошибками.
 */
@Composable
internal fun AdvertisementsScreen() {
    val viewModel = hiltViewModel<AdvertisementsScreenViewModel>()
    val advertisements = viewModel.pagedAdvertisementsFlow.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        // Состояние загрузки в начале списка (prepend)
        if (advertisements.loadState.prepend is LoadState.Loading) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        // Ошибка загрузки в начале списка
        if (advertisements.loadState.prepend is LoadState.Error) {
            val error = advertisements.loadState.prepend as LoadState.Error
            item {
                ErrorItem(
                    message = error.error.localizedMessage ?: "Load more error",
                    modifier = Modifier.fillMaxWidth(),
                    onClickRetry = { advertisements.retry() }
                )
            }
        }

        // Элементы списка объявлений
        items(count = advertisements.itemCount) { index ->
            val advertisement = advertisements[index]
            if (advertisement != null) {
                advertisement.AdvertisementPreviewItem()
            } else {
                LoadingItem()
            }
        }

        // Ошибка загрузки в конце списка (append)
        if (advertisements.loadState.append is LoadState.Error) {
            val error = advertisements.loadState.append as LoadState.Error
            item {
                ErrorItem(
                    message = error.error.localizedMessage ?: "Load more error",
                    modifier = Modifier.fillMaxWidth(),
                    onClickRetry = { advertisements.retry() }
                )
            }
        }
        // Состояние загрузки в конце списка
        if (advertisements.loadState.append is LoadState.Loading) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
