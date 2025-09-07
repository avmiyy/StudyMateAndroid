package ru.vafeen.data.network.service

import retrofit2.http.GET
import retrofit2.http.Query
import ru.vafeen.data.network.dto.AdvertisementDTO

/**
 * Сервис для работы с API объявлений
 */
internal interface AdvertisementsService {

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
    @GET("announcement")
    suspend fun getAnnouncements(
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("tags") tags: List<String>? = null,
        @Query("gender") gender: String? = null,
        @Query("min_age") minAge: Int? = null,
        @Query("max_age") maxAge: Int? = null
    ): List<AdvertisementDTO>
}