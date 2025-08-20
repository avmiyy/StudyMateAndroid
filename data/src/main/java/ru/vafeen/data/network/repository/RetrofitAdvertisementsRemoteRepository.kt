package ru.vafeen.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.vafeen.data.converter.toDomain
import ru.vafeen.data.network.getResponseResultWrappedAllErrors
import ru.vafeen.data.network.paging.AdvertisementPagingSource
import ru.vafeen.data.network.service.AdvertisementsService
import ru.vafeen.domain.models.Advertisement
import ru.vafeen.domain.network.repository.AdvertisementsRemoteRepository
import ru.vafeen.domain.network.result.ResponseResult
import javax.inject.Inject

internal class RetrofitAdvertisementsRemoteRepository @Inject constructor(
    private val service: AdvertisementsService
) : AdvertisementsRemoteRepository {

    override suspend fun getAnnouncements(
        page: Int?,
        limit: Int?,
        tags: List<String>?,
        gender: String?,
        minAge: Int?,
        maxAge: Int?
    ): ResponseResult<List<Advertisement>> =
        getResponseResultWrappedAllErrors {
            service.getAnnouncements(
                page = page,
                limit = limit,
                tags = tags,
                gender = gender,
                minAge = minAge,
                maxAge = maxAge
            )
                .map { it.toDomain() }
        }

    override fun getPagedAnnouncements(
        tags: List<String>?,
        gender: String?,
        minAge: Int?,
        maxAge: Int?
    ): Flow<PagingData<Advertisement>> = Pager(
        initialKey = 1,
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = INITIAL_LOAD_SIZE,
            maxSize = MAX_LOAD_SIZE,
            enablePlaceholders = true,
        ),
        pagingSourceFactory = {
            AdvertisementPagingSource(pageSize = PAGE_SIZE) {
                getAnnouncements(
                    page = it.pageIndex,
                    limit = it.pageSize,
                    tags = tags,
                    gender = gender,
                    minAge = minAge,
                    maxAge = maxAge
                )
            }
        },
    ).flow


    companion object {
        private const val PAGE_SIZE = 10
        private const val INITIAL_LOAD_SIZE = PAGE_SIZE * 3
        private const val MAX_LOAD_SIZE = PAGE_SIZE * 5
    }
}