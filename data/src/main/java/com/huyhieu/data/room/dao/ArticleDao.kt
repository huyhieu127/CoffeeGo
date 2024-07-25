package com.huyhieu.data.room.dao

import androidx.room.Dao

//@Dao
//interface ArticleDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertArticle(vararg article: ArticleEntity)
//
//    @Query("SELECT * FROM article WHERE id = :articleId")
//    fun getArticle(articleId: Long): Flow<ArticleEntity?>
//
//    @Query("SELECT * FROM article")
//    fun getArticles(): Flow<List<ArticleEntity>?>
//}