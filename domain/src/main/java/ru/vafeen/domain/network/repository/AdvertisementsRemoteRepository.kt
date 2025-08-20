package ru.vafeen.domain.network.repository

import ru.vafeen.domain.models.Advertisement
import ru.vafeen.domain.network.result.ResponseResult

interface AdvertisementsRemoteRepository {

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
    suspend fun getAnnouncements(
        page: Int? = null,
        limit: Int? = null,
        tags: List<String>? = null,
        gender: String? = null,
        minAge: Int? = null,
        maxAge: Int? = null
    ): ResponseResult<List<Advertisement>>
}