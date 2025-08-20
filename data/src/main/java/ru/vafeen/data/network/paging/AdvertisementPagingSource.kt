package ru.vafeen.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.vafeen.domain.models.Advertisement
import ru.vafeen.domain.network.result.ResponseResult

internal class AdvertisementPagingSource(
    private val pageSize: Int,
    private val getAdvertisements: suspend (AdvertisementPageConfig) -> ResponseResult<List<Advertisement>>
) : PagingSource<Int, Advertisement>() {


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

    // эта функция должна загрузить страницу в которой обновились данные  
    override fun getRefreshKey(state: PagingState<Int, Advertisement>): Int? {
        // это индекс элемента который запрашивался последним  
        val anchorPosition = state.anchorPosition ?: return null
        // конвертировать индекс в номер страницу  
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    data class AdvertisementPageConfig(
        val pageIndex: Int,
        val pageSize: Int,
    )

    companion object {
        private const val START_PAGE = 1
    }
}

