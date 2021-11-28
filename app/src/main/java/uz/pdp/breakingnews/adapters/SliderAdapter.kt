package uz.pdp.breakingnews.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.pdp.breakingnews.R
import uz.pdp.breakingnews.dagger.data.entity.ArticleEntity
import uz.pdp.breakingnews.databinding.SliderItemBinding
import uz.pdp.breakingnews.madels.news.Article

class SliderAdapter(var context: Context, var listener: OnClickSliderListener) : ListAdapter<ArticleEntity, SliderAdapter.VH>(MyDiffUtil()) {

    interface OnClickSliderListener {
        fun onClick(article: ArticleEntity)

        fun onClickSave(article: ArticleEntity)
    }

    class MyDiffUtil: DiffUtil.ItemCallback<ArticleEntity>() {
        override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
            return oldItem.urlToImage == newItem.urlToImage
        }

        override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
            return oldItem == newItem
        }

    }

    inner class VH(var binding: SliderItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(SliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            val article = getItem(position)

            root.setOnClickListener {
                listener.onClick(article)
            }

            cardSave.setOnClickListener {
                article.isSave = !article.isSave
                listener.onClickSave(article)
            }

            authorTxt.text = "by ${article.author}"

            definationTxt.text = article.title

            Glide.with(context).load(article.urlToImage).placeholder(R.drawable.ic_launcher_background).into(bigImage)
        }
    }


}