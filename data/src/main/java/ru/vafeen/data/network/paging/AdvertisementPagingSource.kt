package ru.vafeen.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.vafeen.domain.models.Advertisement
import ru.vafeen.domain.network.result.ResponseResult

/**
 * Пейджинг-источник для загрузки страниц с объявлениями из сети.
 *
 * @property pageSize Размер страницы для загрузки.
 * @property getAdvertisements Функция suspend для получения списка объявлений согласно конфигурации страницы.
 */
internal class AdvertisementPagingSource(
    private val pageSize: Int,
    private val getAdvertisements: suspend (AdvertisementPageConfig) -> ResponseResult<List<Advertisement>>
) : PagingSource<Int, Advertisement>() {

    /**
     * Загружает страницу данных с индексом из параметров.
     *
     * @param params Параметры загрузки, содержащие ключ страницы.
     * @return Результат загрузки — успешная страница или ошибка.
     * @throws Exception В случае ошибки загрузки генерируется исключение.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Advertisement> {
        val pageIndex = params.key ?: START_PAGE
        return try {
            when (val advertisements = getAdvertisements(
                AdvertisementPageConfig(
                    pageIndex = pageIndex,
                    pageSize = pageSize,
                )
            )) {
                is ResponseResult.Error -> throw Exception("unhandled exception")
                is ResponseResult.Success<List<Advertisement>> -> {
                    LoadResult.Page(
                        data = advertisements.data,
                        prevKey = if (pageIndex == START_PAGE) null else pageIndex - 1,
                        nextKey = if (advertisements.data.size < pageSize) null else pageIndex + 1
                    )
                }
            }

        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }

    /**
     * Определяет ключ обновления страницы для повторной загрузки.
     *
     * @param state Состояние пагинации с информацией о позициях и страницах.
     * @return Ключ страницы для обновления или null, если определить невозможно.
     */
    override fun getRefreshKey(state: PagingState<Int, Advertisement>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    /**
     * Конфигурация для запроса страницы объявлений.
     *
     * @property pageIndex Индекс страницы для загрузки.
     * @property pageSize Количество элементов на странице.
     */
    data class AdvertisementPageConfig(
        val pageIndex: Int,
        val pageSize: Int,
    )

    private companion object {
        /**
         * Номер начальной страницы пагинации.
         */
        const val START_PAGE = 1
    }
}
