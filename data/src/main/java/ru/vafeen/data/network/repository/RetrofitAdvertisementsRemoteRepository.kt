package ru.vafeen.data.network.repository

import android.util.Log
import ru.vafeen.data.converter.toDomain
import ru.vafeen.data.network.getResponseResultWrappedAllErrors
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
                .also {
                    Log.d("error", it.joinToString(separator = "\n"))
                }
                .map { it.toDomain() }
        }


}