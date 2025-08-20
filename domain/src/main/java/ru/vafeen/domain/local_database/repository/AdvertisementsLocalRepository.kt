package ru.vafeen.domain.local_database.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.vafeen.domain.models.Advertisement

interface AdvertisementsLocalRepository {
    /**
     * Получает список объявлений с возможностью фильтрации
     *
     * @param page Номер страницы (начиная с 1)
     * @param limit Количество элементов на странице
     * @param tags Список тегов для фильтрации
     * @param gender Пол для фильтрации
     * @param minAge Минимальный возраст
     * @param maxAge Максимальный возраст
     * @return Список DTO объявлений
     */
    suspend fun getPagedAnnouncements(
        page: Int? = null,
        limit: Int? = null,
        tags: List<String>? = null,
        gender: String? = null,
        minAge: Int? = null,
        maxAge: Int? = null
    ): Flow<PagingData<List<Advertisement>>>
}