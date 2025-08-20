package ru.vafeen.data.converter

import android.graphics.Color
import ru.vafeen.data.network.dto.AdvertisementDTO
import ru.vafeen.domain.models.Advertisement

/**
 * Конвертирует DTO в доменную модель
 */
internal fun AdvertisementDTO.toDomain(): Advertisement {
    return Advertisement(
        id = this.id,
        title = this.title,
        tags = this.tags.map { it.toDomain() },
        bgColor = this.bgColor?.hslStringToColor(), // Возвращает Color
        userId = this.userId,
        userName = this.userName,
        authorName = this.name
    )
}

/**
 * Конвертирует DTO тега в доменную модель тега
 */
internal fun AdvertisementDTO.TagDTO.toDomain(): Advertisement.Tag =
    Advertisement.Tag(
        id = this.id,
        name = this.name,
        color = this.color.hslStringToColor() // Возвращает Color
    )

/**
 * Конвертирует HSL строку в объект Color
 * @param this Строка формата "hsl(360, 100%, 50%)"
 * @return Объект Color
 * @throws IllegalArgumentException если формат строки неверный
 */
fun String.hslStringToColor(): Color {
    val (h, s, l) = parseHslString(this)
    return hslToColor(h, s, l)
}

/**
 * Парсит HSL строку в компоненты
 * @param hslString Строка формата "hsl(360, 100%, 50%)"
 * @return Triple значений (hue, saturation, lightness)
 * @throws IllegalArgumentException если формат строки неверный
 */
private fun parseHslString(hslString: String): Triple<Float, Float, Float> {
    val values = hslString
        .removePrefix("hsl(")
        .removeSuffix(")")
        .split(',')
        .takeIf { it.size == 3 }
        ?.map { it.trim().removeSuffix("%").toFloat() }
        ?: throw IllegalArgumentException("Invalid HSL format: $hslString")

    return Triple(
        values[0].coerceIn(0f, 360f),
        values[1].coerceIn(0f, 100f) / 100f,
        values[2].coerceIn(0f, 100f) / 100f
    )
}

/**
 * Конвертирует HSL в Color
 * @param hue Оттенок (0-360)
 * @param saturation Насыщенность (0-1)
 * @param lightness Яркость (0-1)
 * @return Объект Color
 */
private fun hslToColor(hue: Float, saturation: Float, lightness: Float): Color {
    val (r, g, b) = hslToRgb(hue, saturation, lightness)
    return Color.valueOf(r, g, b)
}

/**
 * Конвертирует HSL в RGB компоненты
 */
private fun hslToRgb(hue: Float, saturation: Float, lightness: Float): Triple<Float, Float, Float> {
    // Конвертируем HSL в HSV
    val value = lightness + saturation * lightness.coerceAtMost(1f - lightness)
    val newSaturation = if (value == 0f) 0f else 2f * (1f - lightness / value)

    // Конвертируем HSV в RGB
    return hsvToRgb(hue, newSaturation, value)
}

/**
 * Конвертирует HSV в RGB компоненты
 */
private fun hsvToRgb(hue: Float, saturation: Float, value: Float): Triple<Float, Float, Float> {
    val hh = hue / 60f
    val i = hh.toInt() % 6
    val f = hh - i

    val p = value * (1 - saturation)
    val q = value * (1 - f * saturation)
    val t = value * (1 - (1 - f) * saturation)

    return when (i) {
        0 -> Triple(value, t, p)
        1 -> Triple(q, value, p)
        2 -> Triple(p, value, t)
        3 -> Triple(p, q, value)
        4 -> Triple(t, p, value)
        5 -> Triple(value, p, q)
        else -> Triple(0f, 0f, 0f)
    }.let {
        Triple(
            it.first.coerceIn(0f, 1f),
            it.second.coerceIn(0f, 1f),
            it.third.coerceIn(0f, 1f)
        )
    }
}