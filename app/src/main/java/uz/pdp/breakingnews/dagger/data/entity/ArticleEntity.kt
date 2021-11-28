package uz.pdp.breakingnews.dagger.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ArticleEntity(

    @PrimaryKey
    var url: String,
    val urlToImage: String?,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    var isSave: Boolean = false
) : Serializable