package ru.vafeen.data.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.vafeen.data.network.repository.RetrofitAdvertisementsRemoteRepository
import ru.vafeen.domain.network.repository.AdvertisementsRemoteRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
internal abstract class NetworkRepositoryModule {
    @Binds
    abstract fun bindsAdvertisementsRepository(
        retrofitAdvertisementsRepository: RetrofitAdvertisementsRemoteRepository
    ): AdvertisementsRemoteRepository
}