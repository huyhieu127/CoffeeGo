package com.huyhieu.data.room.di

import com.huyhieu.data.room.AppRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun providerSongDao(room: AppRoomDataBase) = room.songDao()

    @Singleton
    @Provides
    fun providerSingerDao(room: AppRoomDataBase) = room.singerDao()

    @Singleton
    @Provides
    fun provideNewsDao(room: AppRoomDataBase) = room.newsDao()
}