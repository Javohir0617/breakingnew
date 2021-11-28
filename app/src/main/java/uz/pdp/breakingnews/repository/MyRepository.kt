package uz.pdp.breakingnews.repository

import kotlinx.coroutines.flow.flow
import uz.pdp.breakingnews.dagger.data.dao.ArticleDao
import uz.pdp.breakingnews.dagger.data.entity.ArticleEntity
import uz.pdp.breakingnews.dagger.retro.ApiService
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService, private val articleDao: ArticleDao) {

    suspend fun getNewsRepos() = flow { emit(apiService.getNewsRetro()) }
    suspend fun getSearchRepos(q: String) = flow { emit(apiService.getSearchRetro(q)) }
    suspend fun getCategoryRepos(cat: String) = flow { emit(apiService.getCategoryRetro(cat)) }

    suspend fun addUser(articleEntity: ArticleEntity) = articleDao.addArticle(articleEntity)
    suspend fun deleteUser(articleEntity: ArticleEntity) = articleDao.deleteArticle(articleEntity)
    suspend fun getList() = flow { emit(articleDao.getAllArticles()) }

}