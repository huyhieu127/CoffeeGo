package com.huyhieu.data.room.di

import android.content.Context
import androidx.room.Room
import com.huyhieu.data.room.AppRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providerRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context = context,
            klass = AppRoomDataBase::class.java,
            name = "Coffee-Go"
        ).fallbackToDestructiveMigration()
            .build()
}