package com.shysh.thisartworkdoesnotexist.framework.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shysh.thisartworkdoesnotexist.framework.remote.ThisArtRemoteDataSource
import com.shysh.thisartworkdoesnotexist.framework.remote.ThisArtworkDoesNotExistApi
import com.shysh.thisartworkdoesnotexist.framework.remote.ThisArtworkDoesNotExistApiCommon
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RemoteModule{
    @Provides
    fun provideBaseUrl() = ThisArtworkDoesNotExistApiCommon.API_SERVER

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) =
        retrofit.create(ThisArtworkDoesNotExistApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: ThisArtworkDoesNotExistApi) = ThisArtRemoteDataSource(api)
}