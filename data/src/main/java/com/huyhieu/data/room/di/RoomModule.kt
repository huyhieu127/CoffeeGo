package com.huyhieu.data.room.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

//    @Singleton
//    @Provides
//    fun providerRoomDatabase(@ApplicationContext context: Context) =
//        Room.databaseBuilder(
//            context = context,
//            klass = AppRoomDataBase::class.java,
//            name = "Coffee-Go"
//        ).fallbackToDestructiveMigration()
//            .build()
}