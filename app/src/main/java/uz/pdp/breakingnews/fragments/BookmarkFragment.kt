package uz.pdp.breakingnews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.*
import uz.pdp.breakingnews.App
import uz.pdp.breakingnews.R
import uz.pdp.breakingnews.adapters.RecAdapterNews
import uz.pdp.breakingnews.dagger.data.database.MyDatabaseHelper
import uz.pdp.breakingnews.dagger.data.entity.ArticleEntity
import uz.pdp.breakingnews.databinding.FragmentBookmarkBinding
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class BookmarkFragment : Fragment(R.layout.fragment_bookmark), CoroutineScope {

    private val binding by viewBinding(FragmentBookmarkBinding::bind)
    @Inject
    lateinit var myDatabaseHelper: MyDatabaseHelper
    private lateinit var recAdapterNews: RecAdapterNews
    private lateinit var list: List<ArticleEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.injectBook(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            recAdapterNews = RecAdapterNews(requireContext(), object : RecAdapterNews.OnCardClicked{
                override fun onclick(articleEntity: ArticleEntity) {
                    val bundle = Bundle()
                    bundle.putSerializable("art", articleEntity)
                    findNavController().navigate(R.id.articleFragment, bundle)
                }
            })
            recycle.adapter = recAdapterNews

            launch {
                list = ArrayList(myDatabaseHelper.getHelper().getAllArticles())
                if (list.isNotEmpty()) {
                    recycle.visibility = View.VISIBLE
                    image.visibility = View.GONE
                    recAdapterNews.submitList(list)
                }
            }

        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job()+Dispatchers.Main

}