package uz.pdp.breakingnews.screens

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import uz.pdp.breakingnews.R
import uz.pdp.breakingnews.adapters.SliderAdapter
import uz.pdp.breakingnews.adapters.SliderAdapterEmpty
import uz.pdp.breakingnews.databinding.ActivityOnboardBinding
import uz.pdp.breakingnews.madels.news.Article
import uz.pdp.breakingnews.preference.MyShared
import kotlin.math.abs

class OnboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardBinding
    private lateinit var sliderAdapter: SliderAdapterEmpty
    private lateinit var list: ArrayList<String>
    private var myShared = MyShared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        window?.statusBarColor = Color.WHITE
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myShared = MyShared.getInstance(this)


        loadList()

        binding.apply {
            setViewPager()

            cardNext.setOnClickListener {
                val intent = Intent(this@OnboardActivity, WelcomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setViewPager() {
        binding.apply {
            sliderAdapter = SliderAdapterEmpty(this@OnboardActivity, list)
            viewPager.adapter = sliderAdapter
            dotsIndicator.setViewPager2(viewPager)

            viewPager.clipToPadding = false
            viewPager.clipChildren = false
            viewPager.offscreenPageLimit = 2
            viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(20))
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.75f+r*0.15f
            }
            viewPager.setPageTransformer(compositePageTransformer)

        }
    }

    fun loadList() {
        list = ArrayList()
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS82dkMdNEVf7byvwF_ej03ofxyn4P5bGEQsg&usqp=CAU")
        list.add("data:image/jpeg;base64,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")
        list.add("https://www.germanlw.com/wp-content/uploads/2016/03/News_765x350px.jpg")
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSLJtzRMF7XO63yNWlVSEW25GdwgKVCbdL3w&usqp=CAU")
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYoHDvbdc7JETiZKrvj5UNkkPnywL0tCe_wQ&usqp=CAU")
    }
}