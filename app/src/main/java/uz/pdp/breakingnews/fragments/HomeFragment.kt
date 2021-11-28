package uz.pdp.breakingnews.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import uz.pdp.breakingnews.App
import uz.pdp.breakingnews.R
import uz.pdp.breakingnews.adapters.RecAdapterNews
import uz.pdp.breakingnews.adapters.SliderAdapter
import uz.pdp.breakingnews.adapters.SliderAdapterEmpty
import uz.pdp.breakingnews.adapters.ViewPagerAdapter
import uz.pdp.breakingnews.dagger.data.database.MyDatabaseHelper
import uz.pdp.breakingnews.dagger.data.entity.ArticleEntity
import uz.pdp.breakingnews.databinding.FragmentHomeBinding
import uz.pdp.breakingnews.madels.news.Article
import uz.pdp.breakingnews.utils.NewsResource
import uz.pdp.breakingnews.viewmodels.MyViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.math.abs

class HomeFragment : Fragment(R.layout.fragment_home), CoroutineScope {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    @Inject
    lateinit var myViewModel: MyViewModel
    @Inject
    lateinit var myDatabaseHelper: MyDatabaseHelper
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var list: ArrayList<ArticleEntity>
    private lateinit var listTab: java.util.ArrayList<String>
    private lateinit var recAdapterNews: RecAdapterNews
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        activity?.window?.statusBarColor = Color.WHITE
        viewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.apply {
            loadTabs()
            viewPager2.adapter = viewPagerAdapter
            recAdapterNews = RecAdapterNews(requireContext(), object : RecAdapterNews.OnCardClicked{
                override fun onclick(articleEntity: ArticleEntity) {
                    val bundle = Bundle()
                    bundle.putSerializable("art", articleEntity)
                    findNavController().navigate(R.id.articleFragment, bundle)
                }
            })
            recycle.adapter = recAdapterNews
            launch {
               myViewModel.fetchNews().collect {
                   when(it) {

                       is NewsResource.Loading -> {

                       }
                       is NewsResource.Success -> {
                           list = ArrayList()
                           list.addAll(it.list)
                           setViewPager()
                       }
                       is NewsResource.Error -> {

                       }

                   }
               }
            }
            launch {
                myViewModel.fetchCategory("general").collect {
                    when(it) {

                        is NewsResource.Loading -> {

                        }
                        is NewsResource.Success -> {
                            recAdapterNews.submitList(it.list)
                        }
                        is NewsResource.Error -> {

                        }

                    }
                }
            }


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (searchView.query.toString().isNotEmpty()) {
                        val sheet = BottomSheetFragment.newInstance(searchView.query.toString())
                        sheet.show(childFragmentManager, "DemoBottomSheetFragment")
                    } else {
                        Toast.makeText(requireContext(), "Write query", Toast.LENGTH_SHORT).show()
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return false
                }

            })

            TabLayoutMediator(tablayout, viewPager2) { tab, position ->
                tab.text = listTab[position]
                tab.text.toString().capitalize()
            }.attach()

            tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    launch {
                        myViewModel.fetchCategory(tab?.text.toString()).collect {
                            when(it) {

                                is NewsResource.Loading -> {

                                }
                                is NewsResource.Success -> {
                                    list = ArrayList()
                                    list.clear()
                                    list.addAll(it.list)
                                    sliderAdapter.submitList(list)
                                    sliderAdapter.notifyDataSetChanged()
                                }
                                is NewsResource.Error -> {

                                }

                            }
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })
        }
    }


    private fun setViewPager() {
        binding.apply {
            sliderAdapter = SliderAdapter(requireContext(), object : SliderAdapter.OnClickSliderListener{
                override fun onClick(article: ArticleEntity) {
                    val bundle = Bundle()
                    bundle.putSerializable("art", article)
                    findNavController().navigate(R.id.articleFragment, bundle)
                }

                override fun onClickSave(article: ArticleEntity) {
                    launch {
                        if (article.isSave) {
                            myDatabaseHelper.getHelper().addArticle(article)
                        } else {
                            myDatabaseHelper.getHelper().deleteArticle(article)
                        }
                    }
                }

            })
            viewPager.adapter = sliderAdapter
            sliderAdapter.submitList(list)

            viewPager.clipToPadding = false
            viewPager.clipChildren = false
            viewPager.offscreenPageLimit = 2
            viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(30))
//            compositePageTransformer.addTransformer { page, position ->
//                val r = 1 - abs(position)
//                page.scaleY = 0.75f+r*0.15f
//            }
            viewPager.setPageTransformer(compositePageTransformer)

        }
    }

    private fun loadTabs() {
        listTab = ArrayList()
        listTab.add("business")
        listTab.add("entertainment")
        listTab.add("general")
        listTab.add("health")
        listTab.add("science")
        listTab.add("sports")
        listTab.add("technology")
    }

    override val coroutineContext: CoroutineContext
        get() = Job()+Dispatchers.Main
}