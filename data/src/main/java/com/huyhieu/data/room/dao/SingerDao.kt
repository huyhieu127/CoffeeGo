package com.huyhieu.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.huyhieu.data.room.entity.SingerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SingerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSinger(vararg singer: SingerEntity)

    @Query("SELECT * FROM singer WHERE id = :authorId")
    fun getSinger(authorId: Long): Flow<SingerEntity?>

    @Query("SELECT * FROM singer")
    fun getSingers(): Flow<SingerEntity?>
}