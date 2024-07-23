package com.huyhieu.domain.intractor.article

import com.huyhieu.domain.repository.ArticleRepository
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(private val articleRepository: ArticleRepository) {
    operator fun invoke() =
        articleRepository.getNewsTopHeadlinesDB()
}