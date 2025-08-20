package ru.vafeen.domain.network.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.vafeen.domain.models.Advertisement
import ru.vafeen.domain.network.result.ResponseResult

/**
 * Репозиторий для получения объявлений из удаленного источника.
 */
interface AdvertisementsRemoteRepository {

    /**
     * Получает список объявлений с возможностью фильтрации.
     *
     * @param page Номер страницы (начиная с 1), необязательный.
     * @param limit Количество элементов на странице, необязательный.
     * @param tags Список тегов для фильтрации, необязательный.
     * @param gender Пол для фильтрации, необязательный.
     * @param minAge Минимальный возраст для фильтрации, необязательный.
     * @param maxAge Максимальный возраст для фильтрации, необязательный.
     * @return Результат запроса с оберткой [ResponseResult], содержащий список объявлений.
     */
    suspend fun getAnnouncements(
        page: Int? = null,
        limit: Int? = null,
        tags: List<String>? = null,
        gender: String? = null,
        minAge: Int? = null,
        maxAge: Int? = null
    ): ResponseResult<List<Advertisement>>

    /**
     * Получает постраничный поток объявлений с возможностью фильтрации.
     *
     * @param tags Список тегов для фильтрации, необязательный.
     * @param gender Пол для фильтрации, необязательный.
     * @param minAge Минимальный возраст для фильтрации, необязательный.
     * @param maxAge Максимальный возраст для фильтрации, необязательный.
     * @return Поток постраничных данных объявлений.
     */
    fun getPagedAnnouncements(
        tags: List<String>? = null,
        gender: String? = null,
        minAge: Int? = null,
        maxAge: Int? = null
    ): Flow<PagingData<Advertisement>>
}
