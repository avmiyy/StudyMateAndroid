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

/**
 * Репозиторий для удалённого доступа к объявлениям с использованием Retrofit.
 *
 * @property service Сервис для сетевого взаимодействия с API объявлений.
 */
internal class RetrofitAdvertisementsRemoteRepository @Inject constructor(
    private val service: AdvertisementsService
) : AdvertisementsRemoteRepository {

    /**
     * Получает список объявлений с возможностью указания параметров фильтрации и пагинации.
     *
     * @param page Номер страницы (начинается с 1).
     * @param limit Максимальное количество объявлений на странице.
     * @param tags Список тегов для фильтрации, или null.
     * @param gender Пол для фильтрации, или null.
     * @param minAge Минимальный возраст для фильтрации, или null.
     * @param maxAge Максимальный возраст для фильтрации, или null.
     * @return Результат запроса с обёрткой [ResponseResult], содержащий список доменных моделей объявлений.
     */
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

    /**
     * Возвращает поток постраничных данных объявлений с фильтрацией,
     * поддерживаемый библиотекой Paging 3 и реализованный через [AdvertisementPagingSource].
     *
     * @param tags Список тегов для фильтрации, или null.
     * @param gender Пол для фильтрации, или null.
     * @param minAge Минимальный возраст для фильтрации, или null.
     * @param maxAge Максимальный возраст для фильтрации, или null.
     * @return Поток постраничных данных объявлений типа [Advertisement].
     */
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

    private companion object {
        /**
         * Размер страницы при загрузке объявлений.
         */
        const val PAGE_SIZE = 10

        /**
         * Размер начальной загрузки (кратный PAGE_SIZE).
         */
        const val INITIAL_LOAD_SIZE = PAGE_SIZE * 3

        /**
         * Максимальный размер кэша загруженных элементов.
         */
        const val MAX_LOAD_SIZE = PAGE_SIZE * 5
    }
}
