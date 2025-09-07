package ru.vafeen.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO модель объявления
 * @param id ID объявления
 * @param title Заголовок
 * @param tags Список тегов
 * @param bgColor Цвет фона в HSL (nullable)
 * @param userId ID автора
 * @param userName Логин автора
 * @param name Имя автора
 */
data class AdvertisementDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("tags") val tags: List<TagDTO>,
    @SerializedName("bg_color") val bgColor: String?,
    @SerializedName("user_id") val userId: Long,
    @SerializedName("user_name") val userName: String,
    @SerializedName("name") val name: String
) {
    /**
     * DTO модель тега
     * @param id ID тега
     * @param name Название тега
     * @param color Цвет в HSL формате
     */
    data class TagDTO(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("color") val color: String
    )
}