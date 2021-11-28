package uz.pdp.breakingnews.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.pdp.breakingnews.MainActivity
import uz.pdp.breakingnews.R
import uz.pdp.breakingnews.dagger.data.entity.ArticleEntity
import uz.pdp.breakingnews.databinding.FragmentArticleBinding
import uz.pdp.breakingnews.madels.news.Article

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val binding by viewBinding(FragmentArticleBinding::bind)
    private lateinit var article: ArticleEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getSerializable("art") as ArticleEntity
        }
        (activity as MainActivity).hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        activity?.window?.statusBarColor = Color.TRANSPARENT


        binding.apply {

            titleTv.text = article.title

            newsDataTv.text = article.description

            Glide.with(requireActivity()).load(article.urlToImage).into(image)
            Glide.with(requireActivity()).load(article.urlToImage).into(imageOnBg)

            backImg.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).show()
    }
}