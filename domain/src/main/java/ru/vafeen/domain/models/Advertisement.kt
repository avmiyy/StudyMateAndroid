package ru.vafeen.domain.models

import android.graphics.Color


/**
 * Доменная модель карточки объявления
 *
 * @property id Уникальный идентификатор объявления
 * @property title Заголовок объявления
 * @property tags Список тегов объявления
 * @property bgColor Цвет фона карточки
 * @property userId ID автора объявления
 * @property userName Логин автора объявления
 * @property authorName Отображаемое имя автора
 */
data class Advertisement(
    val id: Long,
    val title: String,
    val tags: List<Tag>,
    val bgColor: Color?,
    val userId: Long,
    val userName: String,
    val authorName: String
) {
    /**
     * Модель тега объявления
     *
     * @property id Уникальный идентификатор тега
     * @property name Название тега
     * @property color Цвет тега
     */
    data class Tag(
        val id: Long,
        val name: String,
        val color: Color,
    )
}