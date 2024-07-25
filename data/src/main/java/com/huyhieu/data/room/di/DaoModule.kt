package com.huyhieu.data.room.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
//
//    @Singleton
//    @Provides
//    fun providerSongDao(room: AppRoomDataBase) = room.songDao()
}