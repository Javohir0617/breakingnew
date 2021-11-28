package uz.pdp.breakingnews.dagger.retro

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.pdp.breakingnews.madels.news.NewsResponse

interface ApiService {

    @GET("everything?q=news&apiKey=6111561047d7436a9b83229b7cbb927f")
    suspend fun getNewsRetro(): Response<NewsResponse>

    @GET("everything?apiKey=6111561047d7436a9b83229b7cbb927f")
    suspend fun getSearchRetro(@Query("q") q: String): Response<NewsResponse>

    @GET("top-headlines?country=ru&apiKey=6111561047d7436a9b83229b7cbb927f")
    suspend fun getCategoryRetro(@Query("category")  category: String): Response<NewsResponse>
}
//6111561047d7436a9b83229b7cbb927f