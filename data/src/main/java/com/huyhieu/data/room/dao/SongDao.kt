package com.huyhieu.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.huyhieu.data.room.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(vararg song: SongEntity)

    @Query("SELECT * FROM song WHERE id = :songId")
    fun getSong(songId: Long): Flow<SongEntity?>

    @Query("SELECT * FROM song")
    fun getSongs(): Flow<SongEntity?>
}