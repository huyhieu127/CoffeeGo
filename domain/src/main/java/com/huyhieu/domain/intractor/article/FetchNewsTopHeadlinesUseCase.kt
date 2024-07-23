package com.huyhieu.domain.intractor.article

import com.huyhieu.domain.repository.ArticleRepository
import javax.inject.Inject
import javax.inject.Singleton

class FetchNewsTopHeadlinesUseCase @Inject constructor(private val articleRepository: ArticleRepository) {
    operator fun invoke(country: String) =
        articleRepository.fetchNewsTopHeadlines(country)
}