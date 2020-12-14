package com.shysh.thisartworkdoesnotexist.framework.di

import com.shysh.thisartworkdoesnotexist.framework.ThisArtRepository
import com.shysh.thisartworkdoesnotexist.framework.local.ThisArtLocalDataSource
import com.shysh.thisartworkdoesnotexist.framework.remote.ThisArtRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: ThisArtRemoteDataSource,
        localDataSource: ThisArtLocalDataSource
    ) = ThisArtRepository(remoteDataSource, localDataSource)
}