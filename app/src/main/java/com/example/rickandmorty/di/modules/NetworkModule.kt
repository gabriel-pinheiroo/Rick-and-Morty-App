package com.example.rickandmorty.di.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.rickandmorty.network.service.CharacterService
import com.example.rickandmorty.network.service.EpisodeService
import com.example.rickandmorty.network.service.LocationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .apply {
            addInterceptor(chuckerInterceptor)
        }
        .build()


    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context,
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }


    @Provides
    fun provideCharacterApi(
        retrofit: Retrofit
    ): CharacterService = retrofit.create(CharacterService::class.java)

    @Provides
    fun provideLocationApi(
        retrofit: Retrofit
    ): LocationService = retrofit.create(LocationService::class.java)

    @Provides
    fun provideEpisodeApi(
        retrofit: Retrofit
    ): EpisodeService = retrofit.create(EpisodeService::class.java)
}