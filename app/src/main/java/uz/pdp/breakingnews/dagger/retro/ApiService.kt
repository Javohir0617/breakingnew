package uz.pdp.breakingnews.dagger.retro

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.pdp.breakingnews.madels.news.NewsResponse

interface ApiService {

    @GET("everything?q=news&apiKey=8d5179f81da64dac8f0aefc43bbf5fac")
    suspend fun getNewsRetro(): Response<NewsResponse>

    @GET("everything?apiKey=8d5179f81da64dac8f0aefc43bbf5fac")
    suspend fun getSearchRetro(@Query("q") q: String): Response<NewsResponse>

    @GET("top-headlines?country=us&apiKey=8d5179f81da64dac8f0aefc43bbf5fac")
    suspend fun getCategoryRetro(@Query("category")  category: String): Response<NewsResponse>
}