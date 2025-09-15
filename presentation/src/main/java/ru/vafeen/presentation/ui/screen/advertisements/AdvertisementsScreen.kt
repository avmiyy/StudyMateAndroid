package ru.vafeen.presentation.ui.screen.advertisements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import ru.vafeen.presentation.R
import ru.vafeen.presentation.ui.common.components.AdvertisementPreviewItem
import ru.vafeen.presentation.ui.common.components.ErrorItem
import ru.vafeen.presentation.ui.common.components.LoadingItem
import ru.vafeen.presentation.ui.common.components.RoundedTextFieldWithIcon
import ru.vafeen.presentation.ui.common.components.filters.FiltersBottomSheet
import ru.vafeen.presentation.ui.common.components.filters.FiltersState
import ru.vafeen.presentation.ui.theme.AppTheme

/**
 * Экран для отображения списка объявлений с поддержкой пагинации.
 *
 * Загружает объявления из ViewModel, отображает список с состояниями загрузки и ошибками.
 * Позволяет пользователю выполнять поиск и открывать панель фильтров.
 */
@Composable
internal fun AdvertisementsScreen() {
    val viewModel = hiltViewModel<AdvertisementsViewModel>()
    val advertisements = viewModel.pagedAdvertisementsFlow.collectAsLazyPagingItems()
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            RoundedTextFieldWithIcon(
                modifier = Modifier.weight(1f),
                value = state.searchRequest,
                focusRequester = state.filtersFocusRequester,
                onValueChange = {
                    viewModel
                        .handleIntent(
                            AdvertisementsIntent.SetSearchRequest(it)
                        )
                },
                placeholder = "Поиск объявлений...",
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.search),
                        contentDescription = "Поиск",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(1.dp)
                            .clip(CircleShape)
                            .background(AppTheme.colors.buttonColor)
                            .padding(5.dp)
                            .size(24.dp)
                    )
                }
            )
            Spacer(modifier = Modifier.width(15.dp))
            Icon(
                painter = painterResource(R.drawable.filters),
                contentDescription = "Фильтры",
                tint = Color.White,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel
                            .handleIntent(
                                AdvertisementsIntent
                                    .SetFiltersBottomSheetVisible(true)
                            )
                    }
                    .background(AppTheme.colors.buttonColor)
                    .padding(5.dp)
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
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
                        message = error.error.localizedMessage ?: "Ошибка загрузки",
                        modifier = Modifier.fillMaxWidth(),
                        onClickRetry = { advertisements.retry() }
                    )
                }
            }

            // Элементы списка объявлений
            items(count = advertisements.itemCount) { index ->
                val advertisement = advertisements[index]
                if (advertisement != null) {
                    advertisement.AdvertisementPreviewItem {}
                } else {
                    LoadingItem()
                }
            }

            // Ошибка загрузки в конце списка (append)
            if (advertisements.loadState.append is LoadState.Error) {
                val error = advertisements.loadState.append as LoadState.Error
                item {
                    ErrorItem(
                        message = error.error.localizedMessage ?: "Ошибка загрузки",
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
    if (state.isFiltersVisible) {
        FiltersBottomSheet(
            initialState = FiltersState(),
            applyFilters = {
                // Функция применения фильтров. Может быть расширена позднее.
            }
        ) {
            viewModel.handleIntent(AdvertisementsIntent.SetFiltersBottomSheetVisible(false))
        }
    }
}
