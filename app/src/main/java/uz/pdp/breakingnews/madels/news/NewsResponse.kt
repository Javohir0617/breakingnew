package uz.pdp.breakingnews.madels.news

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)