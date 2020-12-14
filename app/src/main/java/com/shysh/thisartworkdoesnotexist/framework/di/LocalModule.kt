package com.shysh.thisartworkdoesnotexist.framework.di

import android.content.Context
import com.shysh.thisartworkdoesnotexist.framework.local.ThisArtLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class LocalModule{
    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext appContext: Context) = ThisArtLocalDataSource(appContext)

}