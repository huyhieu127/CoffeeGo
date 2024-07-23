package com.huyhieu.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.huyhieu.data.room.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(vararg article: ArticleEntity)

    @Query("SELECT * FROM article WHERE id = :articleId")
    fun getArticle(articleId: Long): Flow<ArticleEntity?>

    @Query("SELECT * FROM article")
    fun getArticles(): Flow<List<ArticleEntity>?>
}