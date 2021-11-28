package uz.pdp.breakingnews.utils

import uz.pdp.breakingnews.dagger.data.entity.ArticleEntity

sealed class NewsResource {

    object Loading: NewsResource()

    class Success(val list: List<ArticleEntity>): NewsResource()

    class Error(val str: String): NewsResource()
}