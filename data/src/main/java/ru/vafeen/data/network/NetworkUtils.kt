package ru.vafeen.data.network

import ru.vafeen.domain.network.result.ResponseResult

/**
 * Обёртка для безопасного выполнения сетевого запроса с полной обработкой ошибок.
 *
 * Эта функция высшего порядка:
 * 1. Выполняет переданный сетевой запрос в блоке try-catch
 * 2. Возвращает успешный результат, завернутый в [ResponseResult.Success], при успешном выполнении
 * 3. Перехватывает любые исключения и возвращает их в виде [ResponseResult.Error]
 * 4. Логирует полный стек-трейс ошибки для отладки (в виде строки)
 *
 * @param T Тип ожидаемого успешного результата запроса.
 * @param response Подвешиваемый лямбда-блок с логикой сетевого запроса.
 * @return [ResponseResult], содержащий либо успешный результат [ResponseResult.Success] с данными,
 * либо ошибку [ResponseResult.Error] с деталями исключения.
 */
internal suspend fun <T> getResponseResultWrappedAllErrors(response: suspend () -> T): ResponseResult<T> =
    try {
        ResponseResult.Success(data = response())
    } catch (e: Exception) {
        ResponseResult.Error(
            stacktrace = "${e.javaClass.simpleName}: ${e.localizedMessage}"
        )
    }
