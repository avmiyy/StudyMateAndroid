package ru.vafeen.data.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vafeen.data.network.service.AdvertisementsService
import ru.vafeen.data.network.service.ApiInfo
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal class NetworkServiceModule {
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit
        .Builder()
        .baseUrl(ApiInfo.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun providesAdvertisementsService(retrofitBuilder: Retrofit.Builder): AdvertisementsService =
        retrofitBuilder
            .build()
            .create(AdvertisementsService::class.java)


}